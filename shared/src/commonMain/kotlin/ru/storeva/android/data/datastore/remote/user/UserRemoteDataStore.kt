package ru.storeva.android.data.datastore.remote.user

import ru.storeva.android.data.api.user_api.model.DeleteUserResponseBody
import ru.storeva.android.data.api.user_api.model.GetUserResponseBody
import ru.storeva.android.data.api.user_api.model.LogoutResponseBody
import ru.storeva.android.data.api.user_api.model.UpdateUserRequestBody
import ru.storeva.android.data.api.user_api.model.UpdateUserResponseBody

interface UserRemoteDataStore {
    suspend fun getUser(): GetUserResponseBody

    suspend fun updateUser(body: UpdateUserRequestBody): UpdateUserResponseBody

    suspend fun deleteUser(): DeleteUserResponseBody

    suspend fun logout(): LogoutResponseBody
}