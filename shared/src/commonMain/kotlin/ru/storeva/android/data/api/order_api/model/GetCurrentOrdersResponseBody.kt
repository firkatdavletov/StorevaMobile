package ru.storeva.android.data.api.order_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.entities.OrderEntity

@Serializable
data class GetCurrentOrdersResponseBody(
    val orders: List<OrderEntity>,
)