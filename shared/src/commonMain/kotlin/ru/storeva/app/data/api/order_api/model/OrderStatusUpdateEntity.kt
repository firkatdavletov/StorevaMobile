package ru.storeva.app.data.api.order_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.domain.models.OrderStatus

@Serializable
data class OrderStatusUpdateEntity(
    val orderId: Long,
    val status: OrderStatus,
)