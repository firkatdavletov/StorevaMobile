package ru.storeva.app.di

import com.arkivanov.decompose.ComponentContext
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.storeva.app.features.cart.CartComponent
import ru.storeva.app.features.cart.CartViewCallbacks
import ru.storeva.app.features.cart.DefaultCartComponent
import ru.storeva.app.navigation.Config

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