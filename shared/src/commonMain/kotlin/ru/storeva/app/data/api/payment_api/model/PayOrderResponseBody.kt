package ru.storeva.app.data.api.payment_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.entities.OrderEntity

@Serializable
data class PayOrderResponseBody(
    val order: OrderEntity,
)