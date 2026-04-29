package ru.storeva.app.data.mapper

import ru.storeva.app.data.entities.ProductEntity
import ru.storeva.app.domain.models.ProductModel

class ProductMapper {
    fun toModel(entity: ProductEntity) =
        ProductModel(
            id = entity.id,
            title = entity.title,
            imageUrl = entity.imageUrl,
            description = entity.description,
            price = entity.price,
            categoryId = entity.categoryId,
            count = 0,
            countStep = entity.countStep,
            unit = entity.unit,
        )

    fun toModel(entities: List<ProductEntity>) = entities.map { toModel(it) }
}