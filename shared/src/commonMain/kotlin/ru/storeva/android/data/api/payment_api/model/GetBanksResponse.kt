package ru.storeva.android.data.api.payment_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.entities.BankInfoEntity

@Serializable
data class GetBanksResponse(
    val banks: List<BankInfoEntity>,
)