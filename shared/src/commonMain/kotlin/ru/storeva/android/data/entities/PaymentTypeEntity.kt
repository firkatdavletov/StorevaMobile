package ru.storeva.android.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class PaymentTypeEntity(
    val key: String,
    val title: String,
)