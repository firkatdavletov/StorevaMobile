package ru.storeva.app.di.factory.home.maintab

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.features.maintab.MainTabComponent

fun interface MainTabComponentFactory {
    fun create(componentContext: ComponentContext): MainTabComponent
}