package ru.storeva.android.data.datastore.remote.auth

import kotlinx.coroutines.flow.SharedFlow
import ru.storeva.android.data.api.auth_api.model.CheckSmsCodeResponseBody
import ru.storeva.android.data.api.auth_api.model.GetAuthTypesResponseBody
import ru.storeva.android.data.api.auth_api.model.VerifyPhoneNumberRequestBody
import ru.storeva.android.data.api.auth_api.model.VerifyPhoneNumberResponseBody
import ru.storeva.android.data.entities.TokenPairEntity

interface AuthRemoteDataStore {
    val updates: SharedFlow<TokenPairEntity>

    suspend fun getAuthTypes(): GetAuthTypesResponseBody

    suspend fun verifyPhoneNumber(request: VerifyPhoneNumberRequestBody): VerifyPhoneNumberResponseBody

    suspend fun checkSmsCode(
        phone: String,
        code: String,
    ): CheckSmsCodeResponseBody

    suspend fun connect(checkId: String)

    suspend fun disconnect()
}