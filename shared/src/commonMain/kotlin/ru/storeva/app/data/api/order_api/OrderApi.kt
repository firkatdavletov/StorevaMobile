package ru.storeva.app.data.api.order_api

import kotlinx.coroutines.flow.SharedFlow
import ru.storeva.app.data.api.order_api.model.CreateOrderRequestBody
import ru.storeva.app.data.api.order_api.model.CreateOrderResponseModel
import ru.storeva.app.data.api.order_api.model.GetCurrentOrdersResponseBody
import ru.storeva.app.data.api.order_api.model.GetOrderByIdRequestBody
import ru.storeva.app.data.api.order_api.model.GetOrderByIdResponse
import ru.storeva.app.data.api.order_api.model.GetOrdersResponseModel
import ru.storeva.app.data.api.order_api.model.OrderStatusUpdateEntity

interface OrderApi {
    val updates: SharedFlow<OrderStatusUpdateEntity>

    suspend fun getOrderById(body: GetOrderByIdRequestBody): GetOrderByIdResponse

    suspend fun currentOrders(): GetCurrentOrdersResponseBody

    suspend fun getOrders(): GetOrdersResponseModel

    suspend fun getOrdersHistory(): GetOrdersResponseModel

    suspend fun createOrder(body: CreateOrderRequestBody): CreateOrderResponseModel

    suspend fun connect()

    suspend fun disconnect()
}