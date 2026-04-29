package ru.storeva.app.di

import org.koin.dsl.module
import ru.storeva.app.data.datastore.local.IosSecurityStorage
import ru.storeva.app.data.datastore.local.SecurityStorage

actual fun platformModule() =
    module {
        single<SecurityStorage> { IosSecurityStorage() }
    }