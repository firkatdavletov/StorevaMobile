package ru.storeva.app.domain.models

data class CartItemModel(
    val productId: Long,
    val title: String,
    val quantity: Int,
    val price: Long,
    val countStep: Int,
    val unit: UnitOfMeasure,
)