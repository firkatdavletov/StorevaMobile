package ru.storeva.app.features.maintab

import com.arkivanov.decompose.ComponentContext
import kotlinx.serialization.builtins.NothingSerializer
import ru.storeva.app.core.component.ComponentErrorHandler
import ru.storeva.app.core.component.FeatureComponent
import ru.storeva.app.core.coroutine.AppDispatchers
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.catalog.CatalogTabComponent

class DefaultMainTabComponent(
    private val dispatchers: AppDispatchers,
    private val errorHandler: ComponentErrorHandler,
    private val snackBarManager: SnackBarManager?,
    componentContext: ComponentContext,
) : FeatureComponent<Unit, Nothing, Nothing>(
        componentContext = componentContext,
        dispatchers = dispatchers,
        errorHandler = errorHandler,
        snackBarManager = snackBarManager,
        initialState = Unit,
    ),
    MainTabComponent {
    override fun onEvent(event: Nothing) {
        TODO("Not yet implemented")
    }
}