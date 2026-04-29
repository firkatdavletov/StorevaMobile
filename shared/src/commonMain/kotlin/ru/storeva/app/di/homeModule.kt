package ru.storeva.app.di

import com.arkivanov.decompose.ComponentContext
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.storeva.app.domain.repositories.CartRepository
import ru.storeva.app.features.home.DefaultHomeComponentOld
import ru.storeva.app.features.home.HomeCallbacks
import ru.storeva.app.features.home.HomeComponentOld

fun homeModule(): Module =
    module {
        single<HomeComponentOld> { (componentContext: ComponentContext, callbacks: HomeCallbacks) ->
            DefaultHomeComponentOld(
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