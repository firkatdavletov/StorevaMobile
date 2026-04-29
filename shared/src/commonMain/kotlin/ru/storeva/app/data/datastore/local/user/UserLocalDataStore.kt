package ru.storeva.app.data.datastore.local.user

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.data.entities.UserEntity

interface UserLocalDataStore {
    fun getUser(): Flow<UserEntity?>

    fun saveUser(userEntity: UserEntity)

    fun deleteUser()
}