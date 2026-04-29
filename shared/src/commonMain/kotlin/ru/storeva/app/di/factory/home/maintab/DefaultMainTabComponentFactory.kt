package ru.storeva.app.di.factory.home.maintab

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.component.ComponentErrorHandler
import ru.storeva.app.core.coroutine.AppDispatchers
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.home.presentation.HomeDependencies
import ru.storeva.app.features.maintab.DefaultMainTabComponent
import ru.storeva.app.features.maintab.MainTabComponent

class DefaultMainTabComponentFactory(
    private val dispatchers: AppDispatchers,
    private val errorHandler: ComponentErrorHandler,
    private val snackBarManager: SnackBarManager?,
    private val dependencies: HomeDependencies,
) : MainTabComponentFactory {

    override fun create(componentContext: ComponentContext): MainTabComponent {
        return DefaultMainTabComponent(
            componentContext = componentContext,
            dispatchers = dispatchers,
            errorHandler = errorHandler,
            snackBarManager = snackBarManager,
        )
    }
}