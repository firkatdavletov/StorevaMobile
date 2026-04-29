package ru.storeva.app.di.factory.home.profile

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.component.ComponentErrorHandler
import ru.storeva.app.core.coroutine.AppDispatchers
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.di.factory.home.profile.ProfileComponentFactory
import ru.storeva.app.features.profile.DefaultProfileTabComponent
import ru.storeva.app.features.profile.ProfileTabComponent

class DefaultProfileComponentFactory(
    private val dispatchers: AppDispatchers,
    private val errorHandler: ComponentErrorHandler,
    private val snackBarManager: SnackBarManager?,
) : ProfileComponentFactory {

    override fun create(componentContext: ComponentContext): ProfileTabComponent {
        return DefaultProfileTabComponent(
            componentContext = componentContext,
            dispatchers = dispatchers,
            errorHandler = errorHandler,
            snackBarManager = snackBarManager,
        )
    }
}