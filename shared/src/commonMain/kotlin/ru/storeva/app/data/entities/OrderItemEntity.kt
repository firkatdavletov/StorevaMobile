package ru.storeva.app.data.entities

import kotlinx.serialization.Serializable
import ru.storeva.app.domain.models.UnitOfMeasure

@Serializable
data class OrderItemEntity(
    val productId: Long,
    val name: String,
    val quantity: Int,
    val price: Long,
    val imageUrl: String?,
    val unit: UnitOfMeasure,
    val totalPrice: Long,
)