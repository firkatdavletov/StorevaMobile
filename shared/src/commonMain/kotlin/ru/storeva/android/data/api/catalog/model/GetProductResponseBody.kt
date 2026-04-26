package ru.storeva.android.data.api.catalog.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.api.ResponseModel
import ru.storeva.android.data.entities.ProductEntity

@Serializable
class GetProductResponseBody(
    val product: ProductEntity?,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel