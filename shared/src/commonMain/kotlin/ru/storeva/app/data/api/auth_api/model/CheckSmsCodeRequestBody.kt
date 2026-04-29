package ru.storeva.app.data.api.auth_api.model

import kotlinx.serialization.Serializable

@Serializable
data class CheckSmsCodeRequestBody(
    val phone: String,
    val code: String,
)