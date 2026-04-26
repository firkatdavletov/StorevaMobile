package ru.storeva.android.domain.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import ru.storeva.android.domain.models.AddressModel
import ru.storeva.android.domain.models.CartModel
import ru.storeva.android.domain.models.DeliveryInfoModel
import ru.storeva.android.domain.models.DeliveryType
import ru.storeva.android.domain.models.ProductModel
import ru.storeva.android.domain.models.ResultModel

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