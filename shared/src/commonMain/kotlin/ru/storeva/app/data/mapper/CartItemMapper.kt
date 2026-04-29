package ru.storeva.app.data.mapper

import ru.storeva.app.data.entities.CartItemEntity
import ru.storeva.app.domain.models.CartItemModel

class CartItemMapper {
    fun toModel(entity: CartItemEntity) =
        CartItemModel(
            productId = entity.productId,
            title = entity.title,
            quantity = entity.quantity,
            price = entity.price,
            countStep = entity.countStep,
            unit = entity.unit,
        )

    fun toModel(entities: List<CartItemEntity>) = entities.map { toModel(it) }
}