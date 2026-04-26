package ru.storeva.android.data.api.user_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.api.ResponseModel
import ru.storeva.android.data.entities.UserEntity

@Serializable
class GetUserResponseBody(
    val user: UserEntity?,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel