package ru.storeva.android.domain.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.models.UserModel

interface UserRepository {
    val userSubject: SharedFlow<UserModel?>

    fun loadUser(): Flow<ResultModel<Boolean>>

    fun deleteUser(): Flow<ResultModel<Boolean>>

    fun logout(): Flow<ResultModel<Boolean>>

    fun update(userModel: UserModel): Flow<ResultModel<Boolean>>
}