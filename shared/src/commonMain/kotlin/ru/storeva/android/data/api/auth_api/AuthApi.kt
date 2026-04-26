package ru.storeva.android.data.api.auth_api

import kotlinx.coroutines.flow.SharedFlow
import ru.storeva.android.data.api.auth_api.model.CheckSmsCodeRequestBody
import ru.storeva.android.data.api.auth_api.model.CheckSmsCodeResponseBody
import ru.storeva.android.data.api.auth_api.model.CreateCartRequestBody
import ru.storeva.android.data.api.auth_api.model.CreateCartResponse
import ru.storeva.android.data.api.auth_api.model.GetAuthTypesResponseBody
import ru.storeva.android.data.api.auth_api.model.RefreshTokenRequestBody
import ru.storeva.android.data.api.auth_api.model.RefreshTokenResponseBody
import ru.storeva.android.data.api.auth_api.model.VerifyPhoneNumberRequestBody
import ru.storeva.android.data.api.auth_api.model.VerifyPhoneNumberResponseBody
import ru.storeva.android.data.entities.TokenPairEntity

interface AuthApi {
    val updates: SharedFlow<TokenPairEntity>

    suspend fun getAuthTypes(): GetAuthTypesResponseBody

    suspend fun verifyPhoneNumber(
        verifyPhoneNumberRequestBody: VerifyPhoneNumberRequestBody,
    ): VerifyPhoneNumberResponseBody

    suspend fun checkSmsCode(body: CheckSmsCodeRequestBody): CheckSmsCodeResponseBody

    suspend fun refreshTokens(refreshTokenRequestBody: RefreshTokenRequestBody): RefreshTokenResponseBody

    suspend fun createCart(body: CreateCartRequestBody): CreateCartResponse

    suspend fun connect(checkId: String)

    suspend fun disconnect()
}