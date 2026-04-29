package ru.storeva.app.features.home.presentation

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.component.ComponentErrorHandler
import ru.storeva.app.core.component.FeatureComponent
import ru.storeva.app.core.coroutine.AppDispatchers
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.cart.CartTabComponent
import ru.storeva.app.features.catalog.CatalogTabComponent
import ru.storeva.app.features.maintab.MainTabComponent
import ru.storeva.app.features.profile.ProfileTabComponent

class DefaultHomeComponent(
    componentContext: ComponentContext,
    dispatchers: AppDispatchers,
    errorHandler: ComponentErrorHandler,
    snackBarManager: SnackBarManager? = null,
    private val dependencies: HomeDependencies,
) : FeatureComponent<HomeComponent.State, HomeEvent, HomeEffect>(
        componentContext,
        initialState = HomeComponent.State(),
        dispatchers = dispatchers,
        errorHandler = errorHandler,
        snackBarManager = snackBarManager,
    ),
    HomeComponent {
    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.TabSelected -> onTabSelected(event.tab)
        }
    }

    override val mainComponent: MainTabComponent
        get() = TODO("Not yet implemented")
    override val catalogComponent: CatalogTabComponent
        get() = TODO("Not yet implemented")
    override val cartComponent: CartTabComponent
        get() = TODO("Not yet implemented")
    override val profileComponent: ProfileTabComponent
        get() = TODO("Not yet implemented")

    override fun onTabSelected(tab: HomeTab) {
        setState {
            copy(selectedTab = tab)
        }
    }
}