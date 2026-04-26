package ru.storeva.android.domain.models

data class CategoryModel(
    val id: Long,
    val title: String,
    val imageUrl: String?,
    val parentCategoryId: Long?,
    val products: List<ProductModel>,
    val selected: Boolean,
)