package ru.storeva.android.domain.usecase.user

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.models.UserModel
import ru.storeva.android.domain.repositories.UserRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class UpdateUserUseCase(
    private val userRepository: UserRepository,
) : IOUseCase<UserModel, ResultModel<Boolean>>() {
    override fun execute(param: UserModel): Flow<ResultModel<Boolean>> {
        return userRepository.update(param)
    }
}