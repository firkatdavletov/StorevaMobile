package ru.storeva.app.data.entities

import kotlinx.serialization.Serializable
import ru.storeva.app.domain.models.UnitOfMeasure

@Serializable
data class CartItemEntity(
    val productId: Long,
    val title: String,
    val quantity: Int,
    val price: Long,
    val countStep: Int,
    val unit: UnitOfMeasure,
)