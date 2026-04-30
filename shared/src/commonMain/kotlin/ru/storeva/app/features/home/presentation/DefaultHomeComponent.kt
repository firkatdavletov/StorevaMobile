package ru.storeva.app.features.home.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
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
    dependencies: HomeDependencies,
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

    override val mainComponent: MainTabComponent =
        dependencies.mainTabComponentFactory.create(
            componentContext = childContext(key = "main_tab"),
        )
    override val catalogComponent: CatalogTabComponent =
        dependencies.catalogComponentFactory.create(
            componentContext = childContext("catalog_tab"),
        )
    override val cartComponent: CartTabComponent =
        dependencies.cartComponentFactory.create(
            componentContext = childContext(key = "cart_tab"),
        )
    override val profileComponent: ProfileTabComponent =
        dependencies.profileComponentFactory.create(
            componentContext = childContext("profile_tab"),
        )

    override fun onTabSelected(tab: HomeTab) {
        setState {
            copy(selectedTab = tab, selectedTabIndex = tab.ordinal)
        }
    }

    override fun onTabSelectedByIndex(index: Int) {
        val tab = when (index) {
            HomeTab.Main.ordinal -> HomeTab.Main
            HomeTab.Catalog.ordinal -> HomeTab.Catalog
            HomeTab.Cart.ordinal -> HomeTab.Cart
            else -> HomeTab.Profile
        }

        onTabSelected(tab)
    }
}