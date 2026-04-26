package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.CategoryEntity
import ru.storeva.android.domain.models.CategoryModel

class CategoryMapper(
    private val productMapper: ProductMapper,
) {
    fun toModel(entity: CategoryEntity) =
        CategoryModel(
            id = entity.id,
            parentCategoryId = entity.parentCategory,
            title = entity.title,
            imageUrl = entity.imageUrl,
            products = productMapper.toModel(entity.products),
            selected = false,
        )

    fun toModel(entities: List<CategoryEntity>) = entities.map { toModel(it) }
}