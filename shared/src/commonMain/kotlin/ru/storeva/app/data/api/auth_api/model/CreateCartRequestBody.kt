package ru.storeva.app.data.api.auth_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.entities.AddressEntity
import ru.storeva.app.domain.models.DeliveryType

@Serializable
class CreateCartRequestBody(
    val deviceId: String,
    val deliveryType: DeliveryType,
    val deliveryAddress: AddressEntity?,
    val departmentId: Long,
    val deliveryPrice: Long,
    val freeDeliveryPrice: Long?,
)