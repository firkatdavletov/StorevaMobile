@file:Suppress("ktlint:standard:filename")

package ru.storeva.app.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Storage
import androidx.datastore.preferences.core.Preferences

fun createDataStore(storage: Storage<Preferences>): DataStore<Preferences> = DataStoreFactory.create(storage = storage)

@Suppress("ktlint:standard:property-naming")
internal const val dataStoreFileName = "dice.preferences_pb"