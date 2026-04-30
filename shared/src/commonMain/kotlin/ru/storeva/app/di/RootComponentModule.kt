@file:Suppress("ktlint:standard:filename")

package ru.storeva.app.di

import org.koin.dsl.module
import ru.storeva.app.di.factory.root.DefaultRootComponentFactory
import ru.storeva.app.di.factory.root.RootComponentFactory

val rootComponentModule = module {
    single<RootComponentFactory> {
        DefaultRootComponentFactory(
            homeComponentFactory = get(),
            launchComponentFactory = get(),
        )
    }
}