package ru.storeva.android.data.repositories.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.storeva.android.data.api.auth_api.model.VerifyPhoneNumberRequestBody
import ru.storeva.android.data.datastore.local.SecurityStorage
import ru.storeva.android.data.datastore.remote.auth.AuthRemoteDataStore
import ru.storeva.android.data.mapper.AuthTypeMapper
import ru.storeva.android.data.mapper.TokenPairMapper
import ru.storeva.android.domain.models.AuthTypeModel
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.models.VerifyPhoneNumberModel
import ru.storeva.android.domain.repositories.AuthRepository

class DefaultAuthRepository(
    private val authRemoteDataStore: AuthRemoteDataStore,
    private val securityStorage: SecurityStorage,
    private val authTypeMapper: AuthTypeMapper,
    private val tokenPairMapper: TokenPairMapper,
) : AuthRepository {
    override val updates: Flow<Boolean>
        get() = authRemoteDataStore.updates.map { response ->
            val accessToken = response.access
            val refreshToken = response.refresh

            securityStorage.saveAccessToken(accessToken)
            securityStorage.saveRefreshToken(refreshToken)
            true
        }

    override fun getAuthTypes(): Flow<ResultModel<List<AuthTypeModel>>> {
        return flow {
            emit(ResultModel.Loading)

            val response = authRemoteDataStore.getAuthTypes()

            if (response.success) {
                emit(ResultModel.Success(authTypeMapper.toModel(response.types)))
            } else {
                emit(ResultModel.Error(response.error, response.code))
            }
        }
    }

    override fun verifyPhoneNumber(
        phone: String,
        type: String,
    ): Flow<ResultModel<VerifyPhoneNumberModel>> {
        return flow {
            emit(ResultModel.Loading)
            val request = VerifyPhoneNumberRequestBody(phone, type)
            val response = authRemoteDataStore.verifyPhoneNumber(request)

            if (response.success && response.status == 100) {
                val model = VerifyPhoneNumberModel(
                    success = true,
                    checkId = response.checkId,
                    callPhone = response.callPhone,
                )
                emit(ResultModel.Success(model))
            } else {
                emit(ResultModel.Error(response.error, response.code))
            }
        }
    }

    override fun verifyCode(
        phone: String,
        code: String,
    ): Flow<Boolean> {
        return flow {
            val response = authRemoteDataStore.checkSmsCode(phone, code)

            if (response.success && response.tokens != null) {
                val accessToken = response.tokens.access
                val refreshToken = response.tokens.refresh

                securityStorage.saveAccessToken(accessToken)
                securityStorage.saveRefreshToken(refreshToken)

                emit(true)
            } else {
                emit(false)
            }
        }
    }

    override suspend fun connect(checkId: String) {
        authRemoteDataStore.connect(checkId)
    }

    override suspend fun disconnect() {
        authRemoteDataStore.disconnect()
    }
}