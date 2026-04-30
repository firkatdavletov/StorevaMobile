package ru.storeva.app.di.factory.launch

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.component.ComponentErrorHandler
import ru.storeva.app.core.coroutine.AppDispatchers
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.di.factory.home.cart.CartComponentFactory
import ru.storeva.app.features.cart.CartTabComponent
import ru.storeva.app.features.cart.DefaultCartTabComponent
import ru.storeva.app.features.launch.domain.usecase.LoadLaunchDataUseCase
import ru.storeva.app.features.launch.presentation.DefaultLaunchComponent
import ru.storeva.app.features.launch.presentation.LaunchComponent
import ru.storeva.app.features.launch.presentation.LaunchNavigationCallbacks

class DefaultLaunchComponentFactory(
    private val dispatchers: AppDispatchers,
    private val errorHandler: ComponentErrorHandler,
    private val snackBarManager: SnackBarManager?,
    private val loadLaunchDataUseCase: LoadLaunchDataUseCase,
) : LaunchComponentFactory {
    override fun create(
        componentContext: ComponentContext,
        output: LaunchComponent.Output,
    ): LaunchComponent {
        return DefaultLaunchComponent(
            componentContext = componentContext,
            dispatchers = dispatchers,
            errorHandler = errorHandler,
            snackBarManager = snackBarManager,
            loadLaunchDataUseCase = loadLaunchDataUseCase,
            output = output,
        )
    }
}