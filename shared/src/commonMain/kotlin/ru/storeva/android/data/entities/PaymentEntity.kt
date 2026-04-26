package ru.storeva.android.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class PaymentEntity(
    val success: Boolean,
    val message: String? = null,
    val model: PaymentModelEntity? = null,
    val paymentType: String,
)