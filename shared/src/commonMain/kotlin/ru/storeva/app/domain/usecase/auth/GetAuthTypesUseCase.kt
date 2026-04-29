package ru.storeva.app.domain.usecase.auth

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.AuthTypeModel
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.AuthRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class GetAuthTypesUseCase(
    private val authRepository: AuthRepository,
) : IOUseCase<Unit, ResultModel<List<AuthTypeModel>>>() {
    override fun execute(param: Unit): Flow<ResultModel<List<AuthTypeModel>>> {
        return authRepository.getAuthTypes()
    }
}