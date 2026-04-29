package ru.storeva.app.data.datastore.remote.auth

import kotlinx.coroutines.flow.SharedFlow
import ru.storeva.app.data.api.auth_api.AuthApi
import ru.storeva.app.data.api.auth_api.model.CheckSmsCodeRequestBody
import ru.storeva.app.data.api.auth_api.model.CheckSmsCodeResponseBody
import ru.storeva.app.data.api.auth_api.model.GetAuthTypesResponseBody
import ru.storeva.app.data.api.auth_api.model.VerifyPhoneNumberRequestBody
import ru.storeva.app.data.api.auth_api.model.VerifyPhoneNumberResponseBody
import ru.storeva.app.data.entities.TokenPairEntity

class DefaultAuthRemoteDatStore(
    private val authApi: AuthApi,
) : AuthRemoteDataStore {
    override val updates: SharedFlow<TokenPairEntity>
        get() = authApi.updates

    override suspend fun getAuthTypes(): GetAuthTypesResponseBody {
        return authApi.getAuthTypes()
    }

    override suspend fun verifyPhoneNumber(request: VerifyPhoneNumberRequestBody): VerifyPhoneNumberResponseBody {
        return authApi.verifyPhoneNumber(request)
    }

    override suspend fun checkSmsCode(
        phone: String,
        code: String,
    ): CheckSmsCodeResponseBody {
        val request = CheckSmsCodeRequestBody(phone, code)
        return authApi.checkSmsCode(request)
    }

    override suspend fun connect(checkId: String) {
        authApi.connect(checkId)
    }

    override suspend fun disconnect() {
        authApi.disconnect()
    }
}