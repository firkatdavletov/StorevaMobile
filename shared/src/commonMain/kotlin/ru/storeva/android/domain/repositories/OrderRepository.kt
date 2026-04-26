package ru.storeva.android.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.AddressModel
import ru.storeva.android.domain.models.DeliveryType
import ru.storeva.android.domain.models.OrderItemModel
import ru.storeva.android.domain.models.OrderModel
import ru.storeva.android.domain.models.ResultModel

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