package ru.storeva.app.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.AddressModel
import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.domain.models.OrderItemModel
import ru.storeva.app.domain.models.OrderModel
import ru.storeva.app.domain.models.ResultModel

interface OrderRepository {
    val ordersSubject: Flow<List<OrderModel>>

//    val orderWsSubject: Flow<OrderUpdateStatusModel>
    fun getOrderById(id: Long): Flow<ResultModel<OrderModel>>

    fun getCurrentOrders(): Flow<List<OrderModel>>

    fun getOrders(): Flow<List<OrderModel>>

    fun getOrdersHistory(): Flow<List<OrderModel>>

    fun createOrder(
        deliveryType: DeliveryType,
        deliveryAddress: AddressModel?,
        departmentId: Long,
        products: List<OrderItemModel>,
        amount: Long,
        deliveryPrice: Long,
        comment: String?,
    ): Flow<ResultModel<OrderModel>>

    suspend fun clearOrders()

    suspend fun connect()

    suspend fun disconnect()
}