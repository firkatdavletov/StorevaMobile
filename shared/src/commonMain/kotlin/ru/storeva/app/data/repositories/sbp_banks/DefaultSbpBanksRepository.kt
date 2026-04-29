package ru.storeva.app.data.repositories.sbp_banks

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.storeva.app.data.api.payment_api.PaymentApi
import ru.storeva.app.data.mapper.BankInfoMapper
import ru.storeva.app.domain.models.BankInfoModel
import ru.storeva.app.domain.repositories.SbpBanksRepository

class DefaultSbpBanksRepository(
    private val api: PaymentApi,
    private val mapper: BankInfoMapper,
) : SbpBanksRepository {
    override fun getBanks(canStoreToken: Boolean): Flow<List<BankInfoModel>> {
        return if (canStoreToken) {
            flow {
                emit(api.getSubBanks())
            }.map { mapper.toModel(it.banks) }
        } else {
            flow {
                emit(api.getQrBanks())
            }.map { mapper.toModel(it.banks) }
        }
    }
}