package ru.storeva.android.data.api.order_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.entities.AddressEntity
import ru.storeva.android.data.entities.OrderItemEntity
import ru.storeva.android.domain.models.DeliveryType

@Serializable
class CreateOrderRequestBody(
    val deliveryType: DeliveryType,
    val deliveryAddress: AddressEntity?,
    val comment: String?,
    val products: List<OrderItemEntity>,
    val departmentId: Long,
    val amount: Long,
    val deliveryPrice: Long,
)