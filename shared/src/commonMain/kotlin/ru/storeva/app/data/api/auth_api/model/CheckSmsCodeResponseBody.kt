package ru.storeva.app.data.api.auth_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.api.ResponseModel
import ru.storeva.app.data.entities.VerifyPhoneResponseModel

@Serializable
data class CheckSmsCodeResponseBody(
    val tokens: VerifyPhoneResponseModel?,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel