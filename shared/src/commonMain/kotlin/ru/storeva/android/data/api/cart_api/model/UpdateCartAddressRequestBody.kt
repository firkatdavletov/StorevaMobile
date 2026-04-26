package ru.storeva.android.data.api.cart_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.entities.AddressEntity
import ru.storeva.android.data.entities.DeliveryInfoEntity
import ru.storeva.android.domain.models.DeliveryType

@Serializable
data class UpdateCartAddressRequestBody(
    val deliveryType: DeliveryType,
    val deliveryAddress: AddressEntity?,
    val departmentId: Long?,
    val deliveryInfo: DeliveryInfoEntity,
    val comment: String?,
)