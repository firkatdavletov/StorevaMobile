package ru.storeva.android.data.entities

import kotlinx.serialization.Serializable
import ru.storeva.android.domain.models.UnitOfMeasure

@Serializable
data class CartItemEntity(
    val productId: Long,
    val title: String,
    val quantity: Int,
    val price: Long,
    val countStep: Int,
    val unit: UnitOfMeasure,
)