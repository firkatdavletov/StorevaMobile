package ru.storeva.app.features.home.di

import org.koin.dsl.module
import ru.storeva.app.di.factory.home.DefaultHomeComponentFactory
import ru.storeva.app.di.factory.home.HomeComponentFactory
import ru.storeva.app.di.factory.home.cart.CartComponentFactory
import ru.storeva.app.di.factory.home.catalog.CatalogComponentFactory
import ru.storeva.app.di.factory.home.maintab.MainTabComponentFactory
import ru.storeva.app.di.factory.home.profile.ProfileComponentFactory
import ru.storeva.app.features.cart.DefaultCartTabComponent
import ru.storeva.app.features.catalog.DefaultCatalogTabComponent
import ru.storeva.app.features.home.presentation.HomeDependencies
import ru.storeva.app.features.maintab.DefaultMainTabComponent
import ru.storeva.app.features.profile.DefaultProfileTabComponent

val homeModule = module {
    single<HomeComponentFactory> {
        DefaultHomeComponentFactory(
            dispatchers = get(),
            errorHandler = get(),
            snackBarManager = get(),
            cartRepository = get(),
            dependencies = get(),
        )
    }

    single<HomeDependencies> {
        HomeDependencies(
            cartRepository = get(),
            catalogComponentFactory = get(),
            cartComponentFactory = get(),
            profileComponentFactory = get(),
            mainTabComponentFactory = get(),
        )
    }

    single<CatalogComponentFactory> {
        CatalogComponentFactory { componentContext ->
            DefaultCatalogTabComponent(
                componentContext = componentContext,
                dispatchers = get(),
                errorHandler = get(),
                snackBarManager = get(),
            )
        }
    }

    single<CartComponentFactory> {
        CartComponentFactory { componentContext ->
            DefaultCartTabComponent(
                componentContext = componentContext,
                dispatchers = get(),
                errorHandler = get(),
                snackBarManager = get(),
            )
        }
    }

    single<MainTabComponentFactory> {
        MainTabComponentFactory { componentContext ->
            DefaultMainTabComponent(
                componentContext = componentContext,
                dispatchers = get(),
                errorHandler = get(),
                snackBarManager = get(),
            )
        }
    }

    single<ProfileComponentFactory> {
        ProfileComponentFactory { componentContext ->
            DefaultProfileTabComponent(
                componentContext = componentContext,
                dispatchers = get(),
                errorHandler = get(),
                snackBarManager = get(),
            )
        }
    }
}