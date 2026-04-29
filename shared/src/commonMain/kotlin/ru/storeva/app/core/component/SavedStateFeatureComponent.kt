package ru.storeva.app.core.component

import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.KSerializer
import ru.storeva.app.core.coroutine.AppDispatchers

abstract class SavedStateFeatureComponent<State : Any, Event : Any, Effect : Any>(
    componentContext: ComponentContext,
    initialState: State,
    private val stateKey: String,
    private val serializer: KSerializer<State>,
    dispatchers: AppDispatchers,
    errorHandler: ComponentErrorHandler = DefaultComponentErrorHandler(),
) : FeatureComponent<State, Event, Effect>(
        componentContext = componentContext,
        initialState = componentContext.stateKeeper.consume(
            key = stateKey,
            strategy = serializer,
        ) ?: initialState,
        dispatchers = dispatchers,
        errorHandler = errorHandler,
    ) {
    init {
        stateKeeper.register(
            key = stateKey,
            strategy = serializer,
        ) {
            getState()
        }
    }
}