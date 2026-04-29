package ru.storeva.app.di.factory.home.cart

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.features.cart.CartTabComponent

fun interface CartComponentFactory {
    fun create(componentContext: ComponentContext): CartTabComponent
}