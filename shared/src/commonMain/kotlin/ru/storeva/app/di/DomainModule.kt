package ru.storeva.app.di

import org.koin.dsl.module
import ru.storeva.app.features.launch.di.launchModule

val domainModule = module {
    includes(
        coreComponentModule,
        launchModule,
    )
}