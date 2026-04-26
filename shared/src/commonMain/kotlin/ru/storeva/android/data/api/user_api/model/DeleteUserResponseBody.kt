package ru.storeva.android.data.api.user_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.api.ResponseModel

@Serializable
data class DeleteUserResponseBody(
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel