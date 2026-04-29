package ru.storeva.app.data.datastore.local.user

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.storeva.app.data.entities.UserEntity

class DefaultUserLocalDataStore : UserLocalDataStore {
    private var userEntity: UserEntity? = null

    override fun getUser(): Flow<UserEntity?> {
        return flow {
            emit(userEntity)
        }
    }

    override fun saveUser(userEntity: UserEntity) {
        this.userEntity = userEntity
    }

    override fun deleteUser() {
        this.userEntity = null
    }
}