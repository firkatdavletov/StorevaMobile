package ru.storeva.android.di

import com.arkivanov.decompose.ComponentContext
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.storeva.android.domain.repositories.CartRepository
import ru.storeva.android.features.home.DefaultHomeComponent
import ru.storeva.android.features.home.HomeCallbacks
import ru.storeva.android.features.home.HomeComponent

fun homeModule(): Module =
    module {
        single<HomeComponent> { (componentContext: ComponentContext, callbacks: HomeCallbacks) ->
            DefaultHomeComponent(
                componentContext = componentContext,
                snackBarManager = get(),
                homeCallbacks = callbacks,
                cartRepository = get<CartRepository>(),
                addToCartUseCase = get(),
                removeFromCartUseCase = get(),
                getCurrentOrderUseCase = get(),
                orderRepository = get(),
                catalogRepository = get(),
                userRepository = get(),
                orderUIModelMapper = get(),
            )
        }
    }