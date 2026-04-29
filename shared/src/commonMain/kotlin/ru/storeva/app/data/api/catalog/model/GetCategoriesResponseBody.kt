package ru.storeva.app.data.api.catalog.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.entities.CategoryEntity

@Serializable
data class GetCategoriesResponseBody(
    val categories: List<CategoryEntity>,
)