package ru.storeva.app.domain.models

data class OrderUpdateStatusModel(
    val orderId: Long,
    val status: OrderStatus,
)