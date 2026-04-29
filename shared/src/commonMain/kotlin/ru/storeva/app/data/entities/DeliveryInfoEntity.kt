package ru.storeva.app.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class DeliveryInfoEntity(
    val deliveryPrice: Long,
    val freeDeliveryPrice: Long?,
)