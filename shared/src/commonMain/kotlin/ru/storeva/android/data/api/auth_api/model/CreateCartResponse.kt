package ru.storeva.android.data.api.auth_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.api.ResponseModel

@Serializable
class CreateCartResponse(
    val token: String?,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel