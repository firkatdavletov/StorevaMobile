package ru.storeva.android.data.datastore.remote.order

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.storeva.android.data.api.order_api.OrderApi
import ru.storeva.android.data.api.order_api.model.CreateOrderRequestBody
import ru.storeva.android.data.api.order_api.model.CreateOrderResponseModel
import ru.storeva.android.data.api.order_api.model.GetOrderByIdRequestBody
import ru.storeva.android.data.api.order_api.model.GetOrderByIdResponse
import ru.storeva.android.data.api.order_api.model.OrderStatusUpdateEntity
import ru.storeva.android.data.entities.OrderEntity

class DefaultOrderRemoteDataStore(
    private val orderApi: OrderApi,
) : OrderRemoteDataStore {
    override val updates: SharedFlow<OrderStatusUpdateEntity>
        get() = orderApi.updates

    override suspend fun getOrderById(body: GetOrderByIdRequestBody): GetOrderByIdResponse {
        return orderApi.getOrderById(body)
    }

    override fun getCurrentOrders(): Flow<List<OrderEntity>> {
        return flow {
            emit(orderApi.currentOrders())
        }.map { it.orders }
    }

    override fun getOrders(): Flow<List<OrderEntity>> {
        return flow {
            emit(orderApi.getOrders())
        }.map { it.orders }
    }

    override fun getOrdersHistory(): Flow<List<OrderEntity>> {
        return flow {
            emit(orderApi.getOrdersHistory())
        }.map { it.orders }
    }

    override suspend fun createOrder(body: CreateOrderRequestBody): CreateOrderResponseModel {
        return orderApi.createOrder(body)
    }

    override suspend fun connect() {
        orderApi.connect()
    }

    override suspend fun disconnect() {
        orderApi.disconnect()
    }
}