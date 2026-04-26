package ru.storeva.android.data.api.cart_api.model

import kotlinx.serialization.Serializable

@Serializable
data class GetCartRequestBody(
    val deviceId: String,
)