package ru.storeva.app.data.mapper

import ru.storeva.app.data.entities.OrderEntity
import ru.storeva.app.domain.models.OrderModel

class OrderMapper(
    private val orderItemMapper: OrderItemMapper,
    private val departmentMapper: DepartmentMapper,
    private val addressModelMapper: AddressModelMapper,
) {
    fun toModel(entity: OrderEntity): OrderModel {
        val items = orderItemMapper.toModel(entity.items)
        return OrderModel(
            id = entity.id,
            status = entity.status,
            items = items,
            deliveryPrice = entity.deliveryPrice,
            totalAmount = entity.totalAmount,
            deliveryType = entity.deliveryType,
            deliveryAddress = entity.deliveryAddress,
            comment = entity.comment,
        )
    }

    fun toModel(entities: List<OrderEntity>) =
        entities.map {
            toModel(it)
        }
}