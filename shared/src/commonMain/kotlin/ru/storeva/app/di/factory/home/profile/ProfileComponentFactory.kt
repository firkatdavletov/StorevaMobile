package ru.storeva.app.di.factory.home.profile

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.features.profile.ProfileTabComponent

fun interface ProfileComponentFactory {
    fun create(componentContext: ComponentContext): ProfileTabComponent
}