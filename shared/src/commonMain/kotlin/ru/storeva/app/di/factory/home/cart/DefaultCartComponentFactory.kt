package ru.storeva.app.di.factory.home.cart

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.component.ComponentErrorHandler
import ru.storeva.app.core.coroutine.AppDispatchers
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.cart.CartTabComponent
import ru.storeva.app.features.cart.DefaultCartTabComponent

class DefaultCartComponentFactory(
    private val dispatchers: AppDispatchers,
    private val errorHandler: ComponentErrorHandler,
    private val snackBarManager: SnackBarManager?,
) : CartComponentFactory {
    override fun create(componentContext: ComponentContext): CartTabComponent {
        return DefaultCartTabComponent(
            componentContext = componentContext,
            dispatchers = dispatchers,
            errorHandler = errorHandler,
            snackBarManager = snackBarManager,
        )
    }
}