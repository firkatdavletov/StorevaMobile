package ru.storeva.app.core.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.storeva.app.core.coroutine.AppDispatchers
import ru.storeva.app.core.snackbar.SnackBarManager
import kotlin.coroutines.cancellation.CancellationException

abstract class FeatureComponent<State : Any, Event : Any, Effect : Any>(
    componentContext: ComponentContext,
    initialState: State,
    private val dispatchers: AppDispatchers,
    private val errorHandler: ComponentErrorHandler = DefaultComponentErrorHandler(),
    private val snackBarManager: SnackBarManager? = null,
) : ComponentContext by componentContext {

    private val lifecycleJobs = mutableMapOf<String, Job>()

    /**
     * Main coroutine scope of the component.
     *
     * Use this scope for all component-owned async work:
     * - initial loading;
     * - handling UI events;
     * - collecting repositories/interactors flows;
     * - sending one-shot effects;
     * - updating component state.
     *
     * The scope is bound to the Decompose/Essenty lifecycle and is cancelled
     * automatically when the component is destroyed.
     *
     * Prefer [launchSafe] or [launchUnique] instead of launching coroutines
     * directly from this scope, because they provide standard error handling.
     */
    protected val componentScope: CoroutineScope =
        componentContext.coroutineScope(dispatchers.main + SupervisorJob())

    /**
     * Observable UI state of the feature.
     *
     * Use this value from platform UI:
     * - Jetpack Compose observes it and renders Android UI;
     * - SwiftUI observes it through the Decompose/Value bridge.
     *
     * State should contain only durable screen data:
     * - loading flags;
     * - form fields;
     * - selected delivery/payment method;
     * - cart items;
     * - validation state;
     * - screen content.
     *
     * Do not put one-shot events here, such as navigation, snackbar messages,
     * opening dialogs, or payment URL redirects. Use [effects] for that.
     */
    private val _state = MutableValue(initialState)
    val state: Value<State> = _state

    /**
     * One-shot UI effects stream.
     *
     * Use effects for actions that must happen once:
     * - navigation;
     * - opening a dialog or bottom sheet;
     * - showing a one-time success message;
     * - opening payment screen;
     * - closing checkout after successful order creation.
     *
     * Important: this flow is based on [Channel.receiveAsFlow], so it is intended
     * to be collected by a single UI collector. Do not collect it in multiple
     * places at the same time, otherwise different collectors may receive
     * different effects.
     */
    private val _effects = Channel<Effect>(capacity = Channel.BUFFERED)
    val effects: Flow<Effect> = _effects.receiveAsFlow()

    private var isStarted: Boolean = false

    /**
     * Indicates whether the component is currently in started lifecycle state.
     *
     * Use this flag only for advanced cases when behavior depends on screen
     * visibility/activity:
     * - suppressing UI messages from stopped screens;
     * - avoiding navigation effects when the component is no longer visible;
     * - deciding whether a background result should be shown immediately.
     *
     * For ordinary business logic, prefer lifecycle callbacks and [launchUnique].
     */
    protected val isComponentStarted: Boolean
        get() = isStarted

    /**
     * Controls whether errors should be shown only while the component is started.
     *
     * Default value is `true`, which is safer for screens inside navigation stack:
     * a stopped component should not usually show snackbar messages over another
     * active screen.
     *
     * Override and return `false` for components that are allowed to show errors
     * even when stopped, for example:
     * - root-level components;
     * - long-running background operations;
     * - global synchronization;
     * - app-wide authorization/session handling.
     */
    protected open val showErrorsOnlyWhenStarted: Boolean = true

    init {
        lifecycle.subscribe(
            object : Lifecycle.Callbacks {
                override fun onCreate() = this@FeatureComponent.onCreate()

                override fun onStart() {
                    isStarted = true
                    this@FeatureComponent.onStart()
                }

                override fun onResume() = this@FeatureComponent.onResume()

                override fun onPause() = this@FeatureComponent.onPause()

                override fun onStop() {
                    this@FeatureComponent.onStop()
                    isStarted = false
                }

                override fun onDestroy() = this@FeatureComponent.handleDestroy()
            },
        )
    }

    /**
     * Main entry point for UI events.
     *
     * Use this method to handle all user actions and UI intents:
     * - button clicks;
     * - text changes;
     * - item selection;
     * - retry actions;
     * - checkout submit;
     * - cart item quantity changes.
     *
     * Keep this method non-blocking. For async work, call [launchSafe] or
     * [launchUnique] from inside the event handler.
     */
    abstract fun onEvent(event: Event)

    /**
     * Called when the component is created.
     *
     * Use this callback for one-time initialization:
     * - initial data loading;
     * - starting long-lived state observers;
     * - restoring additional in-memory data;
     * - preparing child components.
     *
     * Be careful with heavy work here. Prefer launching async work through
     * [launchSafe] or [launchUnique].
     */
    protected open fun onCreate() = Unit

    /**
     * Called when the component becomes started.
     *
     * Use this callback for work that should run only while the screen is active:
     * - collecting UI-related flows;
     * - refreshing visible data;
     * - starting observers that should stop when the screen is no longer active.
     *
     * If you start collectors here, prefer [launchUnique] and cancel them in
     * [onStop] using [cancelJob], unless they should continue running in back stack.
     */
    protected open fun onStart() = Unit

    /**
     * Called when the component becomes resumed.
     *
     * Use this callback for logic that should happen when the screen is fully
     * interactive:
     * - refreshing data after returning from another screen;
     * - resuming camera/map/location related UI logic;
     * - re-checking permissions.
     *
     * Most screens do not need this callback. Prefer [onStart] for ordinary data
     * observation.
     */
    protected open fun onResume() = Unit

    /**
     * Called before the component leaves resumed state.
     *
     * Use this callback to pause UI-sensitive operations:
     * - camera preview;
     * - map gestures/listeners;
     * - temporary UI tracking;
     * - expensive animations or polling.
     *
     * Most business logic should not be cancelled here. Use [onStop] for
     * visibility-bound jobs.
     */
    protected open fun onPause() = Unit

    /**
     * Called when the component is no longer started.
     *
     * Use this callback to stop work that should only run while the screen is
     * visible/active:
     * - screen collectors;
     * - polling;
     * - UI-only observers;
     * - temporary subscriptions.
     *
     * Use [cancelJob] for jobs started with [launchUnique].
     */
    protected open fun onStop() = Unit

    /**
     * Called when the component is destroyed.
     *
     * Use this callback for final cleanup that is not already handled by coroutine
     * cancellation:
     * - unregistering external listeners;
     * - closing platform resources;
     * - clearing references to callbacks.
     *
     * Do not launch new business work from this callback.
     */
    protected open fun onDestroy() = Unit

    private fun handleDestroy() {
        try {
            onDestroy()
        } finally {
            lifecycleJobs.clear()
        }
    }

    /**
     * Updates the current UI state.
     *
     * Use this method for all state changes:
     * - setting loading flags;
     * - applying loaded data;
     * - updating form fields;
     * - changing selected items;
     * - applying validation errors.
     *
     * This method should normally be called from [componentScope], [launchSafe],
     * [launchUnique], or lifecycle callbacks, because the component scope runs on
     * the main dispatcher.
     *
     * Do not use this method for one-time actions such as navigation or snackbar
     * messages. Use [sendEffect] or [sendEffectAsync] for those.
     */
    protected fun setState(reduce: State.() -> State) {
        if (!componentScope.isActive) return

        _state.update { currentState ->
            currentState.reduce()
        }
    }

    /**
     * Returns the latest state snapshot.
     *
     * Use this method when handling events that need the current state:
     * - building checkout submit command;
     * - checking selected delivery/payment method;
     * - reading current form values;
     * - validating before API call.
     *
     * Avoid overusing this method for complex logic. For large screens, prefer
     * explicit state transformations and small private helper methods.
     */
    protected fun getState(): State {
        return state.value
    }

    /**
     * Sends a one-shot effect from a suspend context.
     *
     * Use this method inside [launchSafe], [launchUnique], or another suspend
     * function when you need to trigger a one-time UI action:
     * - navigate to another screen;
     * - open payment URL;
     * - show success dialog;
     * - close current screen;
     * - scroll to field with validation error.
     *
     * Prefer this method when you are already inside a coroutine.
     */
    protected suspend fun sendEffect(effect: Effect) {
        _effects.send(effect)
    }

    /**
     * Sends a one-shot effect from a non-suspend context.
     *
     * Use this method from synchronous places:
     * - inside [onEvent] when no async work is needed;
     * - inside lifecycle callbacks;
     * - inside small synchronous helper methods.
     *
     * Prefer [sendEffect] when you are already inside a suspend function or
     * coroutine block.
     */
    protected fun sendEffectAsync(effect: Effect) {
        launchSafe(showError = false) {
            _effects.send(effect)
        }
    }

    /**
     * Launches a component-owned coroutine with standard error handling.
     *
     * Use this method for ordinary async operations:
     * - loading screen data;
     * - submitting checkout;
     * - applying promo code;
     * - changing cart quantity;
     * - calling interactors/repositories.
     *
     * If [showError] is `true`, thrown exceptions are converted to user-facing
     * messages through [ComponentErrorHandler] and shown through [SnackBarManager],
     * respecting [showErrorsOnlyWhenStarted].
     *
     * Use [onError] when the screen needs custom error handling, for example:
     * - reset `isLoading`;
     * - mark a form field as invalid;
     * - show a screen-specific error state;
     * - send a custom effect.
     *
     * For operations that must not be duplicated, prefer [launchUnique].
     */
    protected fun launchSafe(
        showError: Boolean = true,
        onError: suspend (Exception) -> Unit = { exception ->
            handleError(
                throwable = exception,
                showError = showError,
            )
        },
        block: suspend CoroutineScope.() -> Unit,
    ): Job {
        return componentScope.launch {
            try {
                block()
            } catch (exception: CancellationException) {
                throw exception
            } catch (exception: Exception) {
                runCatching {
                    onError(exception)
                }
            }
        }
    }

    /**
     * Collects a [Flow] inside [launchSafe].
     *
     * Use this method for simple flow collection owned by the component:
     * - observing cart state;
     * - observing user session;
     * - observing delivery methods;
     * - observing local database changes;
     * - observing form validation streams.
     *
     * Be careful when calling this from lifecycle callbacks like [onStart].
     * Repeated lifecycle calls may start multiple collectors. If the collector
     * must be unique, use [launchUnique] instead.
     */
    protected fun <T> collectFlow(
        flow: Flow<T>,
        showError: Boolean = true,
        onError: suspend (Exception) -> Unit = { exception ->
            handleError(
                throwable = exception,
                showError = showError,
            )
        },
        collector: suspend (T) -> Unit,
    ): Job {
        return launchSafe(
            showError = showError,
            onError = onError,
        ) {
            flow.collect { value ->
                collector(value)
            }
        }
    }

    /**
     * Launches a coroutine associated with a unique string key.
     *
     * Use this method when the operation must not be duplicated:
     * - observing cart flow;
     * - loading checkout data;
     * - submitting order;
     * - applying promo code;
     * - recalculating delivery price;
     * - processing payment redirect.
     *
     * If [restart] is `true`, the previous job with the same key is cancelled
     * and a new one is started.
     *
     * If [restart] is `false` and the previous job is still active, the existing
     * job is returned and no new job is launched. This is useful for submit
     * buttons and long-running operations where repeated clicks should be ignored.
     *
     * Use [cancelJob] to manually cancel a job by key, for example in [onStop].
     */
    protected fun launchUnique(
        key: String,
        restart: Boolean = true,
        showError: Boolean = true,
        onError: suspend (Exception) -> Unit = { exception ->
            handleError(
                throwable = exception,
                showError = showError,
            )
        },
        block: suspend CoroutineScope.() -> Unit,
    ): Job {
        val currentJob = lifecycleJobs[key]

        if (!restart && currentJob?.isActive == true) {
            return currentJob
        }

        currentJob?.cancel()

        return launchSafe(
            showError = showError,
            onError = onError,
            block = block,
        ).also { job ->
            lifecycleJobs[key] = job
        }
    }

    /**
     * Cancels a job previously started with [launchUnique].
     *
     * Use this method when a job should be stopped manually:
     * - stop screen-only collectors in [onStop];
     * - cancel search request when query changes;
     * - cancel delivery price calculation when address changes;
     * - cancel polling when screen becomes inactive.
     *
     * Calling this method with an unknown key is safe.
     */
    protected fun cancelJob(key: String) {
        lifecycleJobs.remove(key)?.cancel()
    }

    /**
     * Handles an exception and optionally shows a user-facing error.
     *
     * Use this method from custom [launchSafe] or [launchUnique] error handlers
     * when you need to combine screen-specific state changes with standard error
     * display.
     *
     * Example:
     *
     * ```
     * launchSafe(
     *     onError = { exception ->
     *         setState { copy(isLoading = false) }
     *         handleError(exception)
     *     }
     * ) {
     *     ...
     * }
     * ```
     *
     * If [showErrorsOnlyWhenStarted] is `true`, the snackbar is shown only while
     * the component is started.
     */
    protected suspend fun handleError(
        throwable: Throwable,
        showError: Boolean = true,
    ) {
        val message = errorHandler.toUserMessage(throwable)

        if (showError && (!showErrorsOnlyWhenStarted || isComponentStarted)) {
            snackBarManager?.showError(message)
        }
    }

    /**
     * Shows a user-facing error message from a non-suspend context.
     *
     * Use this method when you already have a ready message:
     * - validation error;
     * - business rule error;
     * - local form error;
     * - manually prepared domain error.
     *
     * For exceptions, prefer [showThrowable] or [handleError].
     */
    protected fun showError(message: String) {
        launchSafe(showError = false) {
            showErrorSuspend(message)
        }
    }

    /**
     * Shows a user-facing error message from a suspend context.
     *
     * Use this method when you are already inside a coroutine or suspend function
     * and have a ready message.
     *
     * For exceptions, prefer [showThrowableSuspend] or [handleError].
     */
    protected suspend fun showErrorSuspend(message: String) {
        snackBarManager?.showError(message)
    }

    /**
     * Converts a [Throwable] to a user-facing message and shows it from a
     * non-suspend context.
     *
     * Use this method when you caught an error manually outside [launchSafe],
     * for example from callback-style APIs.
     *
     * For most coroutine operations, prefer [launchSafe] or [launchUnique] and
     * let them call [handleError] automatically.
     */
    protected fun showThrowable(throwable: Throwable) {
        launchSafe(showError = false) {
            showThrowableSuspend(throwable)
        }
    }

    /**
     * Converts a [Throwable] to a user-facing message and shows it from a suspend
     * context.
     *
     * Use this method when you are already inside a coroutine but cannot or do
     * not want to use [handleError].
     *
     * In normal async feature logic, prefer [launchSafe] / [launchUnique] with
     * default error handling.
     */
    protected suspend fun showThrowableSuspend(throwable: Throwable) {
        snackBarManager?.showError(
            errorHandler.toUserMessage(throwable),
        )
    }
}