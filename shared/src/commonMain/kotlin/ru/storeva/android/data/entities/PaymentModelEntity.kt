package ru.storeva.android.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class PaymentModelEntity(
    val qrUrl: String? = null,
    val orderId: Long?,
    val version: String? = null,
)