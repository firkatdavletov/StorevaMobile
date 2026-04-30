package ru.storeva.app.di.factory.root

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.di.factory.home.HomeComponentFactory
import ru.storeva.app.di.factory.launch.LaunchComponentFactory
import ru.storeva.app.navigation.DefaultRootComponent
import ru.storeva.app.navigation.RootComponent

class DefaultRootComponentFactory(
    private val homeComponentFactory: HomeComponentFactory,
    private val launchComponentFactory: LaunchComponentFactory,
) : RootComponentFactory {

    override fun create(
        componentContext: ComponentContext,
        snackBarManager: SnackBarManager,
    ): RootComponent {
        return DefaultRootComponent(
            componentContext = componentContext,
            homeComponentFactory = homeComponentFactory,
            launchComponentFactory = launchComponentFactory,
            snackBarManager = snackBarManager,
        )
    }
}