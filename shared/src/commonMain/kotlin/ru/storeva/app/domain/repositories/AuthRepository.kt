package ru.storeva.app.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.AuthTypeModel
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.models.VerifyPhoneNumberModel

interface AuthRepository {
    val updates: Flow<Boolean>

    fun getAuthTypes(): Flow<ResultModel<List<AuthTypeModel>>>

    fun verifyPhoneNumber(
        phone: String,
        type: String,
    ): Flow<ResultModel<VerifyPhoneNumberModel>>

    fun verifyCode(
        phone: String,
        code: String,
    ): Flow<Boolean>

    suspend fun connect(checkId: String)

    suspend fun disconnect()
}