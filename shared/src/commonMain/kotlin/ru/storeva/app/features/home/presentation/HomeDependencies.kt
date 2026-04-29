package ru.storeva.app.features.home.presentation

import ru.storeva.app.di.factory.home.cart.CartComponentFactory
import ru.storeva.app.di.factory.home.catalog.CatalogComponentFactory
import ru.storeva.app.di.factory.home.profile.ProfileComponentFactory
import ru.storeva.app.domain.repositories.CartRepository
import ru.storeva.app.features.maintab.MainTabComponent

data class HomeDependencies(
    val cartRepository: CartRepository,
    val catalogComponentFactory: CatalogComponentFactory,
    val cartComponentFactory: CartComponentFactory,
    val profileComponentFactory: ProfileComponentFactory,
    val mainComponent: MainTabComponent,
)