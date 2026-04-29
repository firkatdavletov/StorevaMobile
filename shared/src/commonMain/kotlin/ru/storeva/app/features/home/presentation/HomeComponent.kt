package ru.storeva.app.features.home.presentation

import com.arkivanov.decompose.value.Value
import ru.storeva.app.features.cart.CartTabComponent
import ru.storeva.app.features.catalog.CatalogTabComponent
import ru.storeva.app.features.maintab.MainTabComponent
import ru.storeva.app.features.profile.ProfileTabComponent

interface HomeComponent {

    val state: Value<State>

    val mainComponent: MainTabComponent
    val catalogComponent: CatalogTabComponent
    val cartComponent: CartTabComponent
    val profileComponent: ProfileTabComponent

    fun onTabSelected(tab: HomeTab)

    data class State(
        val selectedTab: HomeTab = HomeTab.Main,
        val cartBadgeCount: Int = 0,
        val ordersBadgeCount: Int = 0,
    )
}