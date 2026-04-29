package ru.storeva.app.data.api.user_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.api.ResponseModel

@Serializable
class LogoutResponseBody(
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel