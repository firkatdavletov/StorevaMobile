package ru.storeva.android.data.api.order_api.model

import ru.storeva.android.data.entities.OrderEntity

data class GetOrdersResponseModel(
    val orders: List<OrderEntity>,
)