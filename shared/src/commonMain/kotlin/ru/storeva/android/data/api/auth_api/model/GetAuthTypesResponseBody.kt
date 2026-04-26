package ru.storeva.android.data.api.auth_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.api.ResponseModel
import ru.storeva.android.data.entities.AuthTypeEntity

@Serializable
data class GetAuthTypesResponseBody(
    val types: List<AuthTypeEntity>,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel