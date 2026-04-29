package ru.storeva.app.data.api.order_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.entities.AddressEntity
import ru.storeva.app.data.entities.OrderItemEntity
import ru.storeva.app.domain.models.DeliveryType

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