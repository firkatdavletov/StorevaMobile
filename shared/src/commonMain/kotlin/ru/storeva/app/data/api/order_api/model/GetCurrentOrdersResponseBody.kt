package ru.storeva.app.data.api.order_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.entities.OrderEntity

@Serializable
data class GetCurrentOrdersResponseBody(
    val orders: List<OrderEntity>,
)