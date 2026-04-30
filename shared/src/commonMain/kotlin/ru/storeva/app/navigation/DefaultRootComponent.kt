package ru.storeva.app.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.items
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.popWhile
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.pushToFront
import com.arkivanov.decompose.value.Value
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.di.factory.home.HomeComponentFactory
import ru.storeva.app.di.factory.launch.LaunchComponentFactory
import ru.storeva.app.features.app_introduction.AppIntroCallbacks
import ru.storeva.app.features.app_introduction.AppIntroductionComponent
import ru.storeva.app.features.authorization.sign_in_component.SignInCallbacks
import ru.storeva.app.features.authorization.sign_in_component.SignInComponent
import ru.storeva.app.features.authorization.verification_component.VerificationComponent
import ru.storeva.app.features.authorization.verification_component.VerifyCallbacks
import ru.storeva.app.features.cart.CartComponent
import ru.storeva.app.features.cart.CartViewCallbacks
import ru.storeva.app.features.catalog.CatalogCallbacks
import ru.storeva.app.features.catalog.CatalogComponent
import ru.storeva.app.features.current_order.CurrentOrderCallbacks
import ru.storeva.app.features.current_order.CurrentOrderComponent
import ru.storeva.app.features.dialogs.delete_user_dialog.DeleteUserComponent
import ru.storeva.app.features.dialogs.delete_user_dialog.DeleteUserDialogCallbacks
import ru.storeva.app.features.dialogs.logout_user_dialog.LogoutUserComponent
import ru.storeva.app.features.dialogs.logout_user_dialog.LogoutUserDialogCallbacks
import ru.storeva.app.features.dialogs.product_card.ProductCardComponent
import ru.storeva.app.features.home.HomeCallbacks
import ru.storeva.app.features.home.HomeComponentOld
import ru.storeva.app.features.launch.presentation.LaunchComponent
import ru.storeva.app.features.launch.presentation.LaunchNavigationCallbacks
import ru.storeva.app.features.map.MapCallbacks
import ru.storeva.app.features.map.MapComponent
import ru.storeva.app.features.payment.PaymentCallbacks
import ru.storeva.app.features.payment.PaymentComponent
import ru.storeva.app.features.profile.ProfileCallbacks
import ru.storeva.app.features.profile.ProfileComponent
import ru.storeva.app.features.search_address.SearchAddressCallbacks
import ru.storeva.app.features.search_address.SearchAddressComponent
import ru.storeva.app.navigation.RootComponent.BottomChild.*
import ru.storeva.app.navigation.RootComponent.Child.AppIntroduction
import ru.storeva.app.navigation.RootComponent.Child.Cart
import ru.storeva.app.navigation.RootComponent.Child.Catalog
import ru.storeva.app.navigation.RootComponent.Child.CurrentOrder
import ru.storeva.app.navigation.RootComponent.Child.Home
import ru.storeva.app.navigation.RootComponent.Child.Launch
import ru.storeva.app.navigation.RootComponent.Child.Payment
import ru.storeva.app.navigation.RootComponent.Child.Profile
import ru.storeva.app.navigation.RootComponent.Child.SearchAddress
import ru.storeva.app.navigation.RootComponent.Child.SelectAddress
import ru.storeva.app.navigation.RootComponent.Child.SignIn
import ru.storeva.app.navigation.RootComponent.Child.Verification

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val homeComponentFactory: HomeComponentFactory,
    private val launchComponentFactory: LaunchComponentFactory,
    override val snackBarManager: SnackBarManager,
) : RootComponent, ComponentContext by componentContext, KoinComponent {

    private val navigation = StackNavigation<Config>()
    private val dialogNavigation = SlotNavigation<DialogConfig>()

    override val childStack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Launch,
        childFactory = ::createChild,
    )

    override val dialogStack: Value<ChildSlot<*, RootComponent.BottomChild>> = childSlot(
        source = dialogNavigation,
        serializer = DialogConfig.serializer(),
        handleBackButton = true,
        childFactory = ::createBottomChild,
    )

    override fun onBackClicked(toIndex: Int) {
        navigation.popTo(toIndex)
    }

    override fun dismissDialog() {
        dialogNavigation.dismiss()
    }

    private fun createChild(
        config: Config,
        componentContext: ComponentContext,
    ): RootComponent.Child {
        return when (config) {
            is Config.AppIntroduction -> {
                AppIntroduction(getAppIntroComponent(componentContext))
            }

            is Config.Launch -> {
                RootComponent.Child.Launch(
                    launchComponentFactory.create(
                        componentContext = componentContext,
                        output = object : LaunchComponent.Output {
                            override fun onLaunchFinished() {
                                navigation.safePush(Config.Home)
                            }
                        },
                    ),
                )
            }

            is Config.SelectAddress -> {
                SelectAddress(getMapComponent(componentContext, config))
            }

            is Config.Home -> {
                Home(
                    component = homeComponentFactory.create(
                        componentContext = componentContext,
                    ),
                )
            }

            is Config.Cart -> {
                Cart(getCartComponent(componentContext, config))
            }

            is Config.Payment -> {
                Payment(getPaymentComponent(componentContext))
            }

            is Config.CurrentOrder -> {
                CurrentOrder(getCurrentOrderComponent(componentContext, config.fromScreen, config.orderId))
            }

            is Config.Catalog -> {
                Catalog(getCatalogComponent(componentContext, config.categoryId, config.title))
            }

            Config.Profile -> {
                Profile(getProfileComponent(componentContext))
            }

            is Config.SignIn -> {
                SignIn(getSignInComponent(componentContext, config))
            }

            is Config.Verification -> {
                Verification(getVerificationComponent(componentContext, config))
            }

            is Config.SearchAddress -> {
                SearchAddress(getSearchAddressComponent(componentContext, config))
            }
        }
    }

    private fun createBottomChild(
        dialogConfig: DialogConfig,
        componentContext: ComponentContext,
    ): RootComponent.BottomChild {
        return when (dialogConfig) {
            is DialogConfig.ProductCard -> {
                ProductCard(getProductCardComponent(componentContext, dialogConfig))
            }

            is DialogConfig.DeleteUser -> {
                DeleteUser(getDeleteUserComponent(componentContext, dialogConfig))
            }

            is DialogConfig.LogoutUser -> {
                LogoutUser(getLogoutUserComponent(componentContext, dialogConfig))
            }
        }
    }

    private fun getLaunchComponent(componentContext: ComponentContext): LaunchComponent {
        val callbacks = LaunchNavigationCallbacks(
            navigateToSelectAddress = { navigation.pushNew(Config.SelectAddress(LaunchComponent::class.simpleName)) },
            navigateToHome = { navigation.pushToFront(Config.Home) },
        )

        return get { parametersOf(componentContext, callbacks) }
    }

    private fun getAppIntroComponent(componentContext: ComponentContext): AppIntroductionComponent {
        val callbacks = AppIntroCallbacks(
            navigateToAuth = {
                navigation.pushToFront(
                    Config.SignIn(AppIntroductionComponent::class.simpleName),
                )
            },
        )
        return get { parametersOf(componentContext, callbacks) }
    }

    private fun getMapComponent(
        componentContent: ComponentContext,
        config: Config.SelectAddress,
    ): MapComponent {
        val callbacks = MapCallbacks(
            navigateBack = {
                navigation.pop()
            },
            navigateToSearchAddress = { fromScreen ->
                navigation.pushToFront(Config.SearchAddress(fromScreen))
            },
            navigateToHome = {
                navigation.pushToFront(Config.Home)
            },
            navigateToPayment = {
                navigation.pushToFront(Config.Payment)
            },
        )
        return get { parametersOf(componentContent, config.fromScreen, callbacks) }
    }

    private fun getHomeComponent(
        componentContent: ComponentContext,
        config: Config.Home,
    ): HomeComponentOld {
        val callbacks = HomeCallbacks(
            navigateToMap = {
                if (!childStack.items.any {
                        it.configuration == Config.SelectAddress(HomeComponentOld::class.simpleName)
                    }
                ) {
                    navigation.pushNew(Config.SelectAddress(HomeComponentOld::class.simpleName))
                } else {
                    navigation.pushToFront(Config.SelectAddress(HomeComponentOld::class.simpleName))
                }
            },
            navigateToCart = {
                navigation.pushToFront(Config.Cart)
            },
            showProductCard = {
                dialogNavigation.activate(DialogConfig.ProductCard(it.id))
            },
            navigateToProfile = {
                navigation.pushToFront(Config.Profile)
            },
            navigateToOrder = { orderId ->
                if (!childStack.items.any {
                        it.configuration == Config.CurrentOrder(HomeComponentOld::class.simpleName, orderId)
                    }
                ) {
                    navigation.pushNew(Config.CurrentOrder(HomeComponentOld::class.simpleName, orderId))
                } else {
                    navigation.pushToFront(Config.CurrentOrder(HomeComponentOld::class.simpleName, orderId))
                }
            },
            navigateToAuthorization = {
                navigation.pushToFront(Config.SignIn(HomeComponentOld::class.simpleName))
            },
            navigateToCatalog = { id, title ->
                navigation.safePush(Config.Catalog(id, title))
            },
        )
        return get { parametersOf(componentContent, callbacks) }
    }

    private fun getCartComponent(
        componentContext: ComponentContext,
        config: Config.Cart,
    ): CartComponent {
        val callbacks = CartViewCallbacks(
            navigateToPayment = {
                navigation.pushToFront(Config.Payment)
            },
            onBackClicked = {
                navigation.pop()
            },
            navigateToLogin = {
                navigation.pushToFront(Config.SignIn(CartComponent::class.simpleName))
            },
        )
        return get { parametersOf(componentContext, config, callbacks) }
    }

    @OptIn(DelicateDecomposeApi::class)
    private fun getPaymentComponent(componentContext: ComponentContext): PaymentComponent {
        val callbacks = PaymentCallbacks(
            navigateBack = {
                navigation.popWhile({ config ->
                    config != Config.Cart
                })
            },
            navigateToOrder = { navigation.push(Config.CurrentOrder(PaymentComponent::class.simpleName, it)) },
            navigateToMap = { navigation.pushNew(Config.SelectAddress(PaymentComponent::class.simpleName)) },
        )
        return get { parametersOf(componentContext, callbacks) }
    }

    private fun getCurrentOrderComponent(
        context: ComponentContext,
        fromScreen: String?,
        orderId: Long,
    ): CurrentOrderComponent {
        val callbacks = CurrentOrderCallbacks(
            navigateToBack = { navigation.pop() },
            navigateToHome = {
                navigation.pushToFront(Config.Home)
            },
        )
        return get {
            parametersOf(context, fromScreen, callbacks, orderId)
        }
    }

    private fun getCatalogComponent(
        context: ComponentContext,
        categoryId: Long,
        title: String,
    ): CatalogComponent {
        val callbacks = CatalogCallbacks(
            onBack = {
                navigation.pop()
            },
            onNavigateToCart = {
                navigation.pushToFront(Config.Cart)
            },
            showProductCard = {
                dialogNavigation.activate(DialogConfig.ProductCard(it))
            },
        )
        return get {
            parametersOf(context, categoryId, title, callbacks)
        }
    }

    private fun getProfileComponent(context: ComponentContext): ProfileComponent {
        val callbacks = ProfileCallbacks(
            navigateBack = { navigation.pop() },
            showDeleteUserDialog = {
                dialogNavigation.activate(DialogConfig.DeleteUser)
            },
            showLogoutUserDialog = {
                dialogNavigation.activate(DialogConfig.LogoutUser)
            },
        )

        return get {
            parametersOf(context, callbacks)
        }
    }

    private fun getSignInComponent(
        componentContext: ComponentContext,
        config: Config.SignIn,
    ): SignInComponent {
        val callbacks = SignInCallbacks(
            navigateToHome = {
                navigation.pushToFront(Config.Home)
            },
            navigateToVerify = { phoneNumber, authType, fromScreen, checkId, callPhone ->
                navigation.pushNew(Config.Verification(fromScreen, phoneNumber, authType, checkId, callPhone))
            },
            onBack = {
                navigation.pop()
            },
        )
        return get {
            parametersOf(
                componentContext,
                config,
                callbacks,
            )
        }
    }

    private fun getVerificationComponent(
        componentContext: ComponentContext,
        config: Config.Verification,
    ): VerificationComponent {
        val callbacks = VerifyCallbacks(
            onBack = {
                navigation.pop()
            },
            navigateToHome = {
                navigation.pushToFront(Config.Home)
            },
            navigateToPayment = {
                navigation.pushToFront(Config.Payment)
            },
        )
        return get {
            parametersOf(
                componentContext,
                config,
                callbacks,
            )
        }
    }

    private fun getSearchAddressComponent(
        componentContext: ComponentContext,
        config: Config.SearchAddress,
    ): SearchAddressComponent {
        val callbacks = SearchAddressCallbacks(
            navigateToHome = {
                navigation.pushToFront(Config.Home)
            },
            navigateBack = {
                navigation.pop()
            },
            navigateToMap = {
                navigation.pop()
            },
            navigateToPayment = {
                navigation.pushToFront(Config.Payment)
            },
        )
        return get {
            parametersOf(
                componentContext,
                callbacks,
                config.fromScreen,
            )
        }
    }

    private fun getProductCardComponent(
        componentContent: ComponentContext,
        dialogConfig: DialogConfig.ProductCard,
    ): ProductCardComponent {
        return get {
            parametersOf(componentContent, dialogConfig)
        }
    }

    private fun getLogoutUserComponent(
        componentContent: ComponentContext,
        dialogConfig: DialogConfig.LogoutUser,
    ): LogoutUserComponent {
        val callbacks = LogoutUserDialogCallbacks(
            onDismiss = {
                dialogNavigation.dismiss()
            },
            onSuccess = {
                dialogNavigation.dismiss()
                navigation.pop()
            },
        )
        return get {
            parametersOf(componentContent, dialogConfig, callbacks)
        }
    }

    private fun getDeleteUserComponent(
        componentContent: ComponentContext,
        dialogConfig: DialogConfig.DeleteUser,
    ): DeleteUserComponent {
        val callbacks = DeleteUserDialogCallbacks(
            onDismiss = {
                dialogNavigation.dismiss()
            },
            onSuccess = {
                dialogNavigation.dismiss()
                navigation.pop()
            },
        )
        return get {
            parametersOf(componentContent, dialogConfig, callbacks)
        }
    }

    private fun StackNavigation<Config>.safePush(config: Config) {
        if (!childStack.items.any { it.configuration == config }) {
            pushNew(config)
        } else {
            pushToFront(config)
        }
    }
}