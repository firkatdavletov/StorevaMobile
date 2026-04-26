package ru.storeva.android.domain.models

data class OrderUpdateStatusModel(
    val orderId: Long,
    val status: OrderStatus,
)