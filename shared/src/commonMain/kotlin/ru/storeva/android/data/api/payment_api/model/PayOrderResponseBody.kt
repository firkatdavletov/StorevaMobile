package ru.storeva.android.data.api.payment_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.entities.OrderEntity

@Serializable
data class PayOrderResponseBody(
    val order: OrderEntity,
)