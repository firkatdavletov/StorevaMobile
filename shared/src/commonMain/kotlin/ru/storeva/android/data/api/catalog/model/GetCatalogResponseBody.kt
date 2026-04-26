package ru.storeva.android.data.api.catalog.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.api.ResponseModel
import ru.storeva.android.data.entities.CategoryEntity

@Serializable
data class GetCatalogResponseBody(
    val catalog: List<CategoryEntity>,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel