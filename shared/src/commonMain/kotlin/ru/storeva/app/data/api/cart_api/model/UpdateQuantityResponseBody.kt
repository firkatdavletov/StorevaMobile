package ru.storeva.app.data.api.cart_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.entities.CartEntity

@Serializable
data class UpdateQuantityResponseBody(
    val cart: CartEntity,
)