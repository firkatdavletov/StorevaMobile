package ru.storeva.app.data.entities

import kotlinx.serialization.Serializable
import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.domain.models.OrderStatus

@Serializable
data class OrderEntity(
    val id: Long,
    val user: UserEntity,
    val status: OrderStatus,
    val deliveryType: DeliveryType,
    val deliveryAddress: String?,
    val deliveryTime: String?,
    val items: List<OrderItemEntity>,
    val deliveryPrice: Long,
    val totalAmount: Long,
    val comment: String?,
    val created: String,
    val modified: String,
)