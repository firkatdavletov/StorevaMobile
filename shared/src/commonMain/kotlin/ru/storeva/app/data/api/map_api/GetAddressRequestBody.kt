package ru.storeva.app.data.api.map_api

import kotlinx.serialization.Serializable
import ru.storeva.app.data.api.ResponseModel
import ru.storeva.app.data.entities.GeoAddressEntity

@Serializable
class GetAddressResponseBody(
    val address: GeoAddressEntity?,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel