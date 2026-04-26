package ru.storeva.android.data.api.order_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.api.ResponseModel
import ru.storeva.android.data.entities.OrderEntity

@Serializable
data class GetOrderByIdResponse(
    val order: OrderEntity?,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel