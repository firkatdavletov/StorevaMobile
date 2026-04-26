package ru.storeva.android.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.storeva.android.features.profile.DefaultProfileComponent
import ru.storeva.android.features.profile.ProfileCallbacks
import ru.storeva.android.features.profile.ProfileComponent

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