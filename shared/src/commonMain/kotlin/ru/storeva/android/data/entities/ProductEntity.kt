package ru.storeva.android.data.entities

import kotlinx.serialization.Serializable
import ru.storeva.android.domain.models.UnitOfMeasure

@Serializable
data class ProductEntity(
    val id: Long,
    val categoryId: Long,
    val title: String,
    val description: String?,
    val price: Long,
    val imageUrl: String?,
    val unit: UnitOfMeasure,
    val displayWeight: String?,
    val countStep: Int,
)