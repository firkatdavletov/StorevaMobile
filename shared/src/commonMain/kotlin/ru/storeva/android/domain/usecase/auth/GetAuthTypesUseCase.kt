package ru.storeva.android.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.AuthTypeModel
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.repositories.AuthRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class GetAuthTypesUseCase(
    private val authRepository: AuthRepository,
) : IOUseCase<Unit, ResultModel<List<AuthTypeModel>>>() {
    override fun execute(param: Unit): Flow<ResultModel<List<AuthTypeModel>>> {
        return authRepository.getAuthTypes()
    }
}