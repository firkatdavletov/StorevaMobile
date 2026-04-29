package ru.storeva.app.data.api.order_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.api.ResponseModel
import ru.storeva.app.data.entities.OrderEntity

@Serializable
data class GetOrderByIdResponse(
    val order: OrderEntity?,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel