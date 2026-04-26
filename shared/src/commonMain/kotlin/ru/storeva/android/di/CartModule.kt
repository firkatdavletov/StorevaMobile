package ru.storeva.android.di

import com.arkivanov.decompose.ComponentContext
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.storeva.android.features.cart.CartComponent
import ru.storeva.android.features.cart.CartViewCallbacks
import ru.storeva.android.features.cart.DefaultCartComponent
import ru.storeva.android.navigation.Config

fun cartModule(): Module =
    module {
        single<CartComponent> { (componentContext: ComponentContext, config: Config.Cart, callbacks: CartViewCallbacks) ->
            DefaultCartComponent(
                componentContext = componentContext,
                callbacks = callbacks,
                loadCartUseCase = get(),
                addToCartUseCase = get(),
                removeFromCartUseCase = get(),
                getProductCardUseCase = get(),
                cartRepository = get(),
                securityStorage = get(),
                snackBarManager = get(),
            )
        }
    }