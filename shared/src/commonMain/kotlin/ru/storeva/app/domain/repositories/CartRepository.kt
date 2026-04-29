package ru.storeva.app.domain.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import ru.storeva.app.domain.models.AddressModel
import ru.storeva.app.domain.models.CartModel
import ru.storeva.app.domain.models.DeliveryInfoModel
import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.domain.models.ProductModel
import ru.storeva.app.domain.models.ResultModel

interface CartRepository {
    val cartSubject: SharedFlow<CartModel>

    fun loadCart(): Flow<ResultModel<Boolean>>

    fun createCart(
        deliveryType: DeliveryType,
        deliveryAddress: AddressModel?,
        departmentId: Long,
        deliveryInfo: DeliveryInfoModel?,
    ): Flow<ResultModel<Boolean>>

    fun updateQuantity(product: ProductModel): Flow<ResultModel<Boolean>>

    fun updateDeliveryAddress(
        deliveryType: DeliveryType,
        deliveryAddress: AddressModel?,
        departmentId: Long,
        deliveryInfo: DeliveryInfoModel,
        comment: String?,
    ): Flow<ResultModel<Boolean>>

    fun removeAll(): Flow<ResultModel<Boolean>>
}