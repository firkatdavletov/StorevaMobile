package ru.storeva.app.data.datastore.remote.user

import ru.storeva.app.data.api.user_api.model.DeleteUserResponseBody
import ru.storeva.app.data.api.user_api.model.GetUserResponseBody
import ru.storeva.app.data.api.user_api.model.LogoutResponseBody
import ru.storeva.app.data.api.user_api.model.UpdateUserRequestBody
import ru.storeva.app.data.api.user_api.model.UpdateUserResponseBody

interface UserRemoteDataStore {
    suspend fun getUser(): GetUserResponseBody

    suspend fun updateUser(body: UpdateUserRequestBody): UpdateUserResponseBody

    suspend fun deleteUser(): DeleteUserResponseBody

    suspend fun logout(): LogoutResponseBody
}