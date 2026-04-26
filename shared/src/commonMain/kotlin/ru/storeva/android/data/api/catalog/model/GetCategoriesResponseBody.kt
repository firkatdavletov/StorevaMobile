package ru.storeva.android.data.api.catalog.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.entities.CategoryEntity

@Serializable
data class GetCategoriesResponseBody(
    val categories: List<CategoryEntity>,
)