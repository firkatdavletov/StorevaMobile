package ru.storeva.android.data.api.order_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.domain.models.OrderStatus

@Serializable
data class OrderStatusUpdateEntity(
    val orderId: Long,
    val status: OrderStatus,
)