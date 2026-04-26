package ru.storeva.android.domain.usecase.sbp_banks

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.BankInfoModel
import ru.storeva.android.domain.repositories.SbpBanksRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class GetSbpBanksUseCase(
    private val repository: SbpBanksRepository,
) : IOUseCase<GetSbpBanksUseCase.Params, List<BankInfoModel>>() {
    override fun execute(param: Params): Flow<List<BankInfoModel>> {
        return repository.getBanks(param.canStoreToken)
    }

    data class Params(val canStoreToken: Boolean)
}