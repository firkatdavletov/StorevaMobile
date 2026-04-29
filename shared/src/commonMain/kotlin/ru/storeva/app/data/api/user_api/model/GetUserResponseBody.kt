package ru.storeva.app.data.api.user_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.api.ResponseModel
import ru.storeva.app.data.entities.UserEntity

@Serializable
class GetUserResponseBody(
    val user: UserEntity?,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel