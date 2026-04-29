package ru.storeva.app.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.BankInfoModel

interface SbpBanksRepository {
    fun getBanks(canStoreToken: Boolean): Flow<List<BankInfoModel>>
}