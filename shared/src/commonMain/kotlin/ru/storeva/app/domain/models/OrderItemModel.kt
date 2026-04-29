package ru.storeva.app.domain.models

data class OrderItemModel(
    val productId: Long,
    val name: String,
    val quantity: Int,
    val price: Long,
    val imageUrl: String?,
    val unit: UnitOfMeasure,
    val totalPrice: Long,
)