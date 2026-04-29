package ru.storeva.app.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.storeva.app.features.profile.DefaultProfileComponent
import ru.storeva.app.features.profile.ProfileCallbacks
import ru.storeva.app.features.profile.ProfileComponent

fun profileModule() =
    module {
        single<ProfileComponent> { (componentContext: ComponentContext, callback: ProfileCallbacks) ->
            DefaultProfileComponent(
                componentContext,
                get(),
                callback,
                get(),
                get(),
            )
        }
    }