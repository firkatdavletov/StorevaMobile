package ru.storeva.app.data.mapper

import ru.storeva.app.data.entities.CartEntity
import ru.storeva.app.domain.models.CartModel

class CartMapper(
    private val departmentMapper: DepartmentMapper,
    private val cityMapper: CityMapper,
    private val cartItemMapper: CartItemMapper,
    private val addressModelMapper: AddressModelMapper,
    private val deliveryInfoMapper: DeliveryInfoMapper,
) {
    fun toModel(entity: CartEntity): CartModel =
        CartModel(
            items = cartItemMapper.toModel(entity.items),
            deliveryType = entity.deliveryType,
            deliveryAddress = entity.deliveryAddress?.let { addressModelMapper.toModel(it) },
            deliveryInfo = deliveryInfoMapper.toModel(entity.deliveryInfo),
            totalPrice = entity.totalPrice,
            department = departmentMapper.toModel(entity.department),
            comment = entity.comment,
        )
}