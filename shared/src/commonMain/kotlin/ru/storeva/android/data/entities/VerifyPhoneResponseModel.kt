package ru.storeva.android.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class VerifyPhoneResponseModel(
    val access: String,
    val refresh: String,
)