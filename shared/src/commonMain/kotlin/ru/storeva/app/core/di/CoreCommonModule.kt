package ru.storeva.app.core.di

import org.koin.dsl.module
import ru.storeva.app.core.component.ComponentErrorHandler
import ru.storeva.app.core.component.DefaultComponentErrorHandler
import ru.storeva.app.core.coroutine.AppDispatchers
import ru.storeva.app.core.coroutine.DefaultAppDispatchers

val coreCommonModule = module {
    single<AppDispatchers> {
        DefaultAppDispatchers()
    }
    single<ComponentErrorHandler> {
        DefaultComponentErrorHandler()
    }
}