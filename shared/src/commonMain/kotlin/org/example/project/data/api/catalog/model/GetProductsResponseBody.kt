package org.example.project.data.api.catalog.model

import kotlinx.serialization.Serializable
import org.example.project.data.api.ResponseModel
import org.example.project.data.entities.CategoryEntity
import org.example.project.data.entities.ProductEntity

@Serializable
data class GetProductsResponseBody(
    val products: List<ProductEntity>,
    override val success: Boolean,
    override val error: String?,
    override val code: Int?,
) : ResponseModel