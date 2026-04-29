package ru.storeva.app.data.api.payment_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.entities.BankInfoEntity

@Serializable
data class GetBanksResponse(
    val banks: List<BankInfoEntity>,
)