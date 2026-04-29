package ru.storeva.app.data.datastore.remote.order

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import ru.storeva.app.data.api.order_api.model.CreateOrderRequestBody
import ru.storeva.app.data.api.order_api.model.CreateOrderResponseModel
import ru.storeva.app.data.api.order_api.model.GetOrderByIdRequestBody
import ru.storeva.app.data.api.order_api.model.GetOrderByIdResponse
import ru.storeva.app.data.api.order_api.model.OrderStatusUpdateEntity
import ru.storeva.app.data.entities.OrderEntity

interface OrderRemoteDataStore {
    val updates: SharedFlow<OrderStatusUpdateEntity>

    fun getCurrentOrders(): Flow<List<OrderEntity>>

    fun getOrders(): Flow<List<OrderEntity>>

    fun getOrdersHistory(): Flow<List<OrderEntity>>

    suspend fun createOrder(body: CreateOrderRequestBody): CreateOrderResponseModel

    suspend fun getOrderById(body: GetOrderByIdRequestBody): GetOrderByIdResponse

    suspend fun connect()

    suspend fun disconnect()
}