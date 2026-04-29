package ru.storeva.app.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.repositories.AuthRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class VerifyCodeUseCase(
    private val authRepository: AuthRepository,
) : IOUseCase<VerifyCodeUseCase.Params, Boolean>() {
    override fun execute(param: Params): Flow<Boolean> {
        return authRepository.verifyCode(param.phone, param.code)
    }

    data class Params(val phone: String, val code: String)
}