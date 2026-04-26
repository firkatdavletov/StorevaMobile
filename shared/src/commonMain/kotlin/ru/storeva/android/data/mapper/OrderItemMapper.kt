package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.OrderItemEntity
import ru.storeva.android.domain.models.OrderItemModel

class OrderItemMapper {
    fun toModel(entity: OrderItemEntity) =
        OrderItemModel(
            productId = entity.productId,
            name = entity.name,
            quantity = entity.quantity,
            price = entity.price,
            imageUrl = entity.imageUrl,
            unit = entity.unit,
            totalPrice = entity.totalPrice,
        )

    fun toModel(entities: List<OrderItemEntity>) = entities.map { toModel(it) }

    fun toEntity(model: OrderItemModel): OrderItemEntity =
        OrderItemEntity(
            productId = model.productId,
            name = model.name,
            quantity = model.quantity,
            price = model.price,
            imageUrl = model.imageUrl,
            unit = model.unit,
            totalPrice = model.totalPrice,
        )

    fun toEntity(models: List<OrderItemModel>): List<OrderItemEntity> = models.map { toEntity(it) }
}