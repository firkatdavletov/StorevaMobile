package ru.storeva.android.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.storeva.android.features.launch.DefaultLaunchComponent
import ru.storeva.android.features.launch.LaunchComponent
import ru.storeva.android.features.launch.LaunchNavigationCallbacks

fun launchModule() =
    module {
        single<LaunchComponent> { (componentContext: ComponentContext, callbacks: LaunchNavigationCallbacks) ->
            DefaultLaunchComponent(
                componentContext = componentContext,
                loadUserUseCase = get(),
                loadCatalogUseCase = get(),
                loadCartUseCase = get(),
                callbacks = callbacks,
                snackBarManager = get(),
                orderRepository = get(),
            )
        }
    }