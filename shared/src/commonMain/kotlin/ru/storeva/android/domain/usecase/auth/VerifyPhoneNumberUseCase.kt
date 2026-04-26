package ru.storeva.android.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.models.VerifyPhoneNumberModel
import ru.storeva.android.domain.repositories.AuthRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class VerifyPhoneNumberUseCase(
    private val authRepository: AuthRepository,
) : IOUseCase<VerifyPhoneNumberUseCase.Params, ResultModel<VerifyPhoneNumberModel>>() {
    override fun execute(param: Params): Flow<ResultModel<VerifyPhoneNumberModel>> {
        return authRepository.verifyPhoneNumber(param.phone, param.type)
    }

    class Params(val phone: String, val type: String)
}