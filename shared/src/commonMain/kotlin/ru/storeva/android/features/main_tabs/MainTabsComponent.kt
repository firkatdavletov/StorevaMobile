package ru.storeva.android.features.main_tabs

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.storeva.android.features.cart.CartComponent
import ru.storeva.android.features.catalog.CatalogComponent
import ru.storeva.android.features.home.HomeComponent
import ru.storeva.android.features.main_tabs.sbp_banks.SbpBanksComponent
import ru.storeva.android.features.search_address.SearchAddressComponent

interface MainTabsComponent {
    val childStack: Value<ChildStack<*, Child>>

    fun onBackClicked(toIndex: Int)

    sealed class Child {
        class HomeChild(val component: HomeComponent) : Child()

        class CatalogChild(val component: CatalogComponent) : Child()

        class CartChild(val component: CartComponent) : Child()

        class SbpBanksChild(val component: SbpBanksComponent) : Child()

        class SearchAddressChild(val component: SearchAddressComponent) : Child()
    }
}