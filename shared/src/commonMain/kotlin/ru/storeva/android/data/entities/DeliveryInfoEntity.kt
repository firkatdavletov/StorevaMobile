package ru.storeva.android.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class DeliveryInfoEntity(
    val deliveryPrice: Long,
    val freeDeliveryPrice: Long?,
)