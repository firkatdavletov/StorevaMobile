package ru.storeva.android.data.repositories.user

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import ru.storeva.android.data.api.user_api.model.UpdateUserRequestBody
import ru.storeva.android.data.datastore.local.user.UserLocalDataStore
import ru.storeva.android.data.datastore.remote.user.UserRemoteDataStore
import ru.storeva.android.data.mapper.UserMapper
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.models.UserModel
import ru.storeva.android.domain.repositories.UserRepository

class DefaultUserRepository(
    private val userRemoteDataStore: UserRemoteDataStore,
    private val userLocalDataStore: UserLocalDataStore,
    private val userMapper: UserMapper,
) : UserRepository {

    private val _userSubject: MutableSharedFlow<UserModel?> = MutableSharedFlow(replay = 1)

    override val userSubject: SharedFlow<UserModel?> = _userSubject.asSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun loadUser(): Flow<ResultModel<Boolean>> {
        return flow {
            emit(ResultModel.Loading)

            val response = userRemoteDataStore.getUser()

            if (response.success && response.user != null) {
                userLocalDataStore.saveUser(response.user)
                _userSubject.emit(userMapper.toModel(response.user))
                emit(ResultModel.Success(true))
            } else {
                emit(ResultModel.Error(response.error, response.code))
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun deleteUser(): Flow<ResultModel<Boolean>> {
        return flow {
            emit(ResultModel.Loading)

            val response = userRemoteDataStore.deleteUser()

            if (response.success) {
                userLocalDataStore.deleteUser()
                _userSubject.emit(null)
                emit(ResultModel.Success(true))
            } else {
                emit(ResultModel.Error(response.error, response.code))
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun logout(): Flow<ResultModel<Boolean>> {
        return flow {
            emit(ResultModel.Loading)

            val response = userRemoteDataStore.logout()

            if (response.success) {
                userLocalDataStore.deleteUser()
                _userSubject.emit(null)
                emit(ResultModel.Success(true))
            } else {
                emit(ResultModel.Error(response.error, response.code))
            }
        }
    }

    override fun update(userModel: UserModel): Flow<ResultModel<Boolean>> {
        return flow {
            emit(ResultModel.Loading)

            val body = UpdateUserRequestBody(userMapper.toEntity(userModel))
            val response = userRemoteDataStore.updateUser(body)

            if (response.success && response.user != null) {
                userLocalDataStore.saveUser(response.user)
                _userSubject.emit(userMapper.toModel(response.user))
                emit(ResultModel.Success(true))
            } else {
                emit(ResultModel.Error(response.error, response.code))
            }
        }
    }
}