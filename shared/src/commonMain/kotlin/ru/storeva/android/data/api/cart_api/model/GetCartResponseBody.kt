package ru.storeva.android.data.api.cart_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.api.ResponseModel
import ru.storeva.android.data.entities.CartEntity

@Serializable
class GetCartResponseBody(
    val cart: CartEntity?,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel