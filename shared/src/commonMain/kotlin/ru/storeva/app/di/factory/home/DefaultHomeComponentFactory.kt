package ru.storeva.app.di.factory.home

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.component.ComponentErrorHandler
import ru.storeva.app.core.coroutine.AppDispatchers
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.domain.repositories.CartRepository
import ru.storeva.app.features.home.presentation.DefaultHomeComponent
import ru.storeva.app.features.home.presentation.HomeComponent
import ru.storeva.app.features.home.presentation.HomeDependencies

class DefaultHomeComponentFactory(
    private val dispatchers: AppDispatchers,
    private val errorHandler: ComponentErrorHandler,
    private val snackBarManager: SnackBarManager?,
    private val cartRepository: CartRepository,
    private val dependencies: HomeDependencies,
) : HomeComponentFactory {

    override fun create(componentContext: ComponentContext): HomeComponent {
        return DefaultHomeComponent(
            componentContext = componentContext,
            dispatchers = dispatchers,
            errorHandler = errorHandler,
            snackBarManager = snackBarManager,
            dependencies = dependencies,
        )
    }
}