package ru.storeva.app.data.datastore.remote.user

import ru.storeva.app.data.api.user_api.UserApi
import ru.storeva.app.data.api.user_api.model.DeleteUserResponseBody
import ru.storeva.app.data.api.user_api.model.GetUserResponseBody
import ru.storeva.app.data.api.user_api.model.LogoutResponseBody
import ru.storeva.app.data.api.user_api.model.UpdateUserRequestBody
import ru.storeva.app.data.api.user_api.model.UpdateUserResponseBody

class DefaultUserRemoteDatastore(
    private val userApi: UserApi,
) : UserRemoteDataStore {
    override suspend fun getUser(): GetUserResponseBody {
        return userApi.getUser()
    }

    override suspend fun deleteUser(): DeleteUserResponseBody {
        return userApi.deleteUser()
    }

    override suspend fun updateUser(body: UpdateUserRequestBody): UpdateUserResponseBody {
        return userApi.updateUser(body)
    }

    override suspend fun logout(): LogoutResponseBody {
        return userApi.logout()
    }
}