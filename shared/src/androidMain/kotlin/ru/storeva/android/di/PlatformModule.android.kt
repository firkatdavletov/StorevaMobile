package ru.storeva.android.di

import org.koin.dsl.module
import ru.storeva.android.data.datastore.local.AndroidSecurityStorage
import ru.storeva.android.data.datastore.local.SecurityStorage

actual fun platformModule() =
    module {
        single<SecurityStorage> { AndroidSecurityStorage() }
    }