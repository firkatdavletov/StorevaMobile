package ru.storeva.app.data.api.order_api.model

import ru.storeva.app.data.entities.OrderEntity

data class GetOrdersResponseModel(
    val orders: List<OrderEntity>,
)