package ru.storeva.app.di

import org.koin.dsl.module
import ru.storeva.app.core.di.coreCommonModule
import ru.storeva.app.features.home.di.homeModule
import ru.storeva.app.features.launch.di.launchModule

val commonModule = module {
    includes(
        coreCommonModule,
        rootComponentModule,
        launchModule,
        homeModule,
    )
}