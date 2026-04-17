package org.example.project.domain.models

data class OrderModel(
    val id: Long,
    val status: OrderStatus,
    val items: List<OrderItemModel>,
    val deliveryPrice: Long,
    val totalAmount: Long,
    val deliveryType: DeliveryType,
    val deliveryAddress: String?,
    val comment: String?,
)