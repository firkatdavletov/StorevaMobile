package ru.storeva.app.data.entities

import kotlinx.serialization.Serializable
import ru.storeva.app.domain.models.DeliveryType

@Serializable
data class CartEntity(
    val items: List<CartItemEntity>,
    val deliveryType: DeliveryType,
    val deliveryAddress: AddressEntity?,
    val deliveryInfo: DeliveryInfoEntity,
    val totalPrice: Long,
    val department: DepartmentEntity,
    val comment: String?,
)