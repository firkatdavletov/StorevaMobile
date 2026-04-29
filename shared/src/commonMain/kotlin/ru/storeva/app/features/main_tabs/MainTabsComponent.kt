package ru.storeva.app.features.main_tabs

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.storeva.app.features.cart.CartComponent
import ru.storeva.app.features.catalog.CatalogComponent
import ru.storeva.app.features.home.HomeComponentOld
import ru.storeva.app.features.main_tabs.sbp_banks.SbpBanksComponent
import ru.storeva.app.features.search_address.SearchAddressComponent

interface MainTabsComponent {
    val childStack: Value<ChildStack<*, Child>>

    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class HomeChild(val component: HomeComponentOld) : Child()

        class CatalogChild(val component: CatalogComponent) : Child()

        class CartChild(val component: CartComponent) : Child()

        class SbpBanksChild(val component: SbpBanksComponent) : Child()

        class SearchAddressChild(val component: SearchAddressComponent) : Child()
    }
}