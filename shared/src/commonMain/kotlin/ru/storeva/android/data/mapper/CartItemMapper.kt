package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.CartItemEntity
import ru.storeva.android.domain.models.CartItemModel

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