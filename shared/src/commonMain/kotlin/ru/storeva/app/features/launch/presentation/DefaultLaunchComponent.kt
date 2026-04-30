package ru.storeva.app.features.launch.presentation

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.component.ComponentErrorHandler
import ru.storeva.app.core.component.FeatureComponent
import ru.storeva.app.core.coroutine.AppDispatchers
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.launch.domain.usecase.LoadLaunchDataUseCase

class DefaultLaunchComponent(
    componentContext: ComponentContext,
    private val loadLaunchDataUseCase: LoadLaunchDataUseCase,
    private val output: LaunchComponent.Output,
    dispatchers: AppDispatchers,
    snackBarManager: SnackBarManager?,
    errorHandler: ComponentErrorHandler,
) : FeatureComponent<LaunchState, LaunchEvent, LaunchEffect>(
        componentContext = componentContext,
        initialState = LaunchState(
            isLoading = true,
            isError = false,
        ),
        dispatchers = dispatchers,
        snackBarManager = snackBarManager,
        errorHandler = errorHandler,
    ),
    LaunchComponent {

    override fun onCreate() {
        loadData()
    }

    override fun onEvent(event: LaunchEvent) {
        when (event) {
            LaunchEvent.RetryClicked -> loadData()
        }
    }

    private fun loadData() {
        launchSafe(
            onError = { throwable ->
                setState {
                    copy(
                        isLoading = false,
                        isError = true,
                    )
                }

                showThrowableSuspend(throwable)
            },
        ) {
            setState {
                copy(
                    isLoading = true,
                    isError = false,
                )
            }

            val result = loadLaunchDataUseCase(Unit)

            setState {
                copy(
                    isLoading = false,
                    isError = false,
                )
            }

            // TODO реализовать логику в дальнейшем
            when {
                result.minSupportedVersion == "0.0" -> {
                    output.onLaunchFinished()
                }

                else -> {}
            }
        }
    }
}