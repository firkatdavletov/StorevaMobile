package ru.storeva.app.di.factory.home

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.features.home.presentation.HomeComponent

fun interface HomeComponentFactory {
    fun create(componentContext: ComponentContext): HomeComponent
}