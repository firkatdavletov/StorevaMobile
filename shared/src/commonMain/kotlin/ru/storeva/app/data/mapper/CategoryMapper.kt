package ru.storeva.app.data.mapper

import ru.storeva.app.data.entities.CategoryEntity
import ru.storeva.app.domain.models.CategoryModel

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