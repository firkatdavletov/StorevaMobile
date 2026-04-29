package ru.storeva.app.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.storeva.app.features.app_introduction.AppIntroCallbacks
import ru.storeva.app.features.app_introduction.AppIntroductionComponent
import ru.storeva.app.features.app_introduction.DefaultAppIntroductionComponent

fun appIntroductionModule() =
    module {
        single<AppIntroductionComponent> { (componentContext: ComponentContext, callbacks: AppIntroCallbacks) ->
            DefaultAppIntroductionComponent(
                componentContext = componentContext,
                callbacks = callbacks,
            )
        }
    }