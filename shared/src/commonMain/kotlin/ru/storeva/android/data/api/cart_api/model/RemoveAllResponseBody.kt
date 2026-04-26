package ru.storeva.android.data.api.cart_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.entities.CartEntity

@Serializable
data class RemoveAllResponseBody(
    val cart: CartEntity,
)