package ru.storeva.app.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class PaymentTypeEntity(
    val key: String,
    val title: String,
)