package ru.storeva.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import ru.storeva.android.feature.app_introduction.AppIntroductionContent
import ru.storeva.android.feature.authorization.AuthorizationContent
import ru.storeva.android.feature.authorization.sign_in.SignInScreen
import ru.storeva.android.feature.authorization.verification.VerificationScreen
import ru.storeva.android.feature.cart.CartScreen
import ru.storeva.android.feature.catalog.CatalogScreen
import ru.storeva.android.feature.current_order.CurrentOrderScreen
import ru.storeva.android.feature.home.HomeScreen
import ru.storeva.android.feature.launch.LaunchScreen
import ru.storeva.android.feature.main_tabs.MainTabsContent
import ru.storeva.android.feature.map_view.MapScreen
import ru.storeva.android.feature.payment.PaymentScreen
import ru.storeva.android.feature.profile.ProfileScreen
import ru.storeva.android.feature.search_address.SearchAddressScreen

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.childStack,
        modifier = modifier,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.Authorization -> AuthorizationContent(child.component)
            is RootComponent.Child.AppIntroduction -> AppIntroductionContent(child.component)
            is RootComponent.Child.MainTabs -> MainTabsContent(child.component)
            is RootComponent.Child.Launch -> LaunchScreen(child.component)
            is RootComponent.Child.SelectAddress -> MapScreen(child.component)
            is RootComponent.Child.Home -> HomeScreen(child.component)
            is RootComponent.Child.Cart -> CartScreen(child.component)
            is RootComponent.Child.Payment -> PaymentScreen(child.component)
            is RootComponent.Child.CurrentOrder -> CurrentOrderScreen(child.component)
            is RootComponent.Child.Catalog -> CatalogScreen(child.component)
            is RootComponent.Child.Profile -> ProfileScreen(child.component)
            is RootComponent.Child.SignIn -> SignInScreen(child.component)
            is RootComponent.Child.Verification -> VerificationScreen(child.component)
            is RootComponent.Child.SearchAddress -> SearchAddressScreen(child.component)
        }
    }
}