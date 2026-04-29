package ru.storeva.app.data.api.cart_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.entities.AddressEntity
import ru.storeva.app.data.entities.DeliveryInfoEntity
import ru.storeva.app.domain.models.DeliveryType

@Serializable
data class UpdateCartAddressRequestBody(
    val deliveryType: DeliveryType,
    val deliveryAddress: AddressEntity?,
    val departmentId: Long?,
    val deliveryInfo: DeliveryInfoEntity,
    val comment: String?,
)