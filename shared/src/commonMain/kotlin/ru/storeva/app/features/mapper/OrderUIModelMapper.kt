package ru.storeva.app.features.mapper

import ru.storeva.app.domain.models.OrderModel
import ru.storeva.app.domain.models.OrderStatus
import ru.storeva.app.features.home.OrderUIModel

class OrderUIModelMapper {
    fun toUIModel(model: OrderModel): OrderUIModel {
        return OrderUIModel(
            id = model.id,
            number = model.id.toString(),
            status = OrderStatus.getTitle(model.status),
            amount = model.totalAmount,
        )
    }
}