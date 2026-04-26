package ru.storeva.android.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.storeva.android.features.app_introduction.AppIntroCallbacks
import ru.storeva.android.features.app_introduction.AppIntroductionComponent
import ru.storeva.android.features.app_introduction.DefaultAppIntroductionComponent

fun appIntroductionModule() =
    module {
        single<AppIntroductionComponent> { (componentContext: ComponentContext, callbacks: AppIntroCallbacks) ->
            DefaultAppIntroductionComponent(
                componentContext = componentContext,
                callbacks = callbacks,
            )
        }
    }