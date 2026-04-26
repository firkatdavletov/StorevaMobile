package ru.storeva.android.data.api.user_api

import ru.storeva.android.data.api.user_api.model.DeleteUserResponseBody
import ru.storeva.android.data.api.user_api.model.GetUserResponseBody
import ru.storeva.android.data.api.user_api.model.LogoutResponseBody
import ru.storeva.android.data.api.user_api.model.UpdateUserRequestBody
import ru.storeva.android.data.api.user_api.model.UpdateUserResponseBody

interface UserApi {
    suspend fun getUser(): GetUserResponseBody

    suspend fun updateUser(updateUserRequestBody: UpdateUserRequestBody): UpdateUserResponseBody

    suspend fun deleteUser(): DeleteUserResponseBody

    suspend fun logout(): LogoutResponseBody
}