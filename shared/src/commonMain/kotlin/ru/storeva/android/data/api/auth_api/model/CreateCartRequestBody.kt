package ru.storeva.android.data.api.auth_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.entities.AddressEntity
import ru.storeva.android.domain.models.DeliveryType

@Serializable
class CreateCartRequestBody(
    val deviceId: String,
    val deliveryType: DeliveryType,
    val deliveryAddress: AddressEntity?,
    val departmentId: Long,
    val deliveryPrice: Long,
    val freeDeliveryPrice: Long?,
)