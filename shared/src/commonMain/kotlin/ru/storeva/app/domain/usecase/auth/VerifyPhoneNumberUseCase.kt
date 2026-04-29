package ru.storeva.app.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.models.VerifyPhoneNumberModel
import ru.storeva.app.domain.repositories.AuthRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class VerifyPhoneNumberUseCase(
    private val authRepository: AuthRepository,
) : IOUseCase<VerifyPhoneNumberUseCase.Params, ResultModel<VerifyPhoneNumberModel>>() {
    override fun execute(param: Params): Flow<ResultModel<VerifyPhoneNumberModel>> {
        return authRepository.verifyPhoneNumber(param.phone, param.type)
    }

    class Params(val phone: String, val type: String)
}