package ru.storeva.app.domain.usecase.user

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.models.UserModel
import ru.storeva.app.domain.repositories.UserRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class UpdateUserUseCase(
    private val userRepository: UserRepository,
) : IOUseCase<UserModel, ResultModel<Boolean>>() {
    override fun execute(param: UserModel): Flow<ResultModel<Boolean>> {
        return userRepository.update(param)
    }
}