package ru.storeva.app.data.api.order_api.model

import kotlinx.serialization.Serializable

@Serializable
data class GetOrderByIdRequestBody(
    val id: Long,
)