package ru.storeva.android.navigation

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.app_introduction.AppIntroductionComponent
import ru.storeva.android.features.authorization.AuthorizationComponent
import ru.storeva.android.features.authorization.sign_in_component.SignInComponent
import ru.storeva.android.features.authorization.verification_component.VerificationComponent
import ru.storeva.android.features.cart.CartComponent
import ru.storeva.android.features.catalog.CatalogComponent
import ru.storeva.android.features.current_order.CurrentOrderComponent
import ru.storeva.android.features.dialogs.delete_user_dialog.DeleteUserComponent
import ru.storeva.android.features.dialogs.logout_user_dialog.LogoutUserComponent
import ru.storeva.android.features.dialogs.product_card.ProductCardComponent
import ru.storeva.android.features.home.HomeComponent
import ru.storeva.android.features.launch.LaunchComponent
import ru.storeva.android.features.main_tabs.MainTabsComponent
import ru.storeva.android.features.map.MapComponent
import ru.storeva.android.features.payment.PaymentComponent
import ru.storeva.android.features.profile.ProfileComponent
import ru.storeva.android.features.search_address.SearchAddressComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>
    val dialogStack: Value<ChildSlot<*, BottomChild>>
    val snackBarManager: SnackBarManager

    fun onBackClicked(toIndex: Int)

    fun dismissDialog()

    sealed class Child {
        class Launch(val component: LaunchComponent) : Child()

        class AppIntroduction(val component: AppIntroductionComponent) : Child()

        class Authorization(val component: AuthorizationComponent) : Child()

        class MainTabs(val component: MainTabsComponent) : Child()

        class Home(val component: HomeComponent) : Child()

        class SelectAddress(val component: MapComponent) : Child()

        class Cart(val component: CartComponent) : Child()

        class Payment(val component: PaymentComponent) : Child()

        class CurrentOrder(val component: CurrentOrderComponent) : Child()

        class Catalog(val component: CatalogComponent) : Child()

        class Profile(val component: ProfileComponent) : Child()

        class SignIn(val component: SignInComponent) : Child()

        class Verification(val component: VerificationComponent) : Child()

        class SearchAddress(val component: SearchAddressComponent) : Child()
    }

    sealed class BottomChild(val id: String) {
        class ProductCard(val component: ProductCardComponent) : BottomChild("ProductCard")

        class LogoutUser(val component: LogoutUserComponent) : BottomChild("logout_user")

        class DeleteUser(val component: DeleteUserComponent) : BottomChild("delete_user")
    }
}