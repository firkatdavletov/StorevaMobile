package ru.storeva.app.data.api.cart_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.api.ResponseModel
import ru.storeva.app.data.entities.CartEntity

@Serializable
data class UpdateCartAddressResponseBody(
    val cart: CartEntity?,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel