package ru.storeva.app.di.factory.home.catalog

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.component.ComponentErrorHandler
import ru.storeva.app.core.coroutine.AppDispatchers
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.catalog.CatalogTabComponent
import ru.storeva.app.features.catalog.DefaultCatalogTabComponent

class DefaultCatalogComponentFactory(
    private val dispatchers: AppDispatchers,
    private val errorHandler: ComponentErrorHandler,
    private val snackBarManager: SnackBarManager?,
) : CatalogComponentFactory {
    override fun create(componentContext: ComponentContext): CatalogTabComponent {
        return DefaultCatalogTabComponent(
            componentContext = componentContext,
            dispatchers = dispatchers,
            errorHandler = errorHandler,
            snackBarManager = snackBarManager,
        )
    }
}