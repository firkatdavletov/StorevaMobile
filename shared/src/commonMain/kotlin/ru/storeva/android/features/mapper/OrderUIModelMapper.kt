package ru.storeva.android.features.mapper

import ru.storeva.android.domain.models.OrderModel
import ru.storeva.android.domain.models.OrderStatus
import ru.storeva.android.features.home.OrderUIModel

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