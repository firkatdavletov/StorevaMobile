package ru.storeva.app.data.mapper

import ru.storeva.app.data.entities.DeliveryInfoEntity
import ru.storeva.app.domain.models.DeliveryInfoModel

class DeliveryInfoMapper {
    fun toModel(entity: DeliveryInfoEntity): DeliveryInfoModel {
        return DeliveryInfoModel(
            deliveryPrice = entity.deliveryPrice,
            freeDeliveryPrice = entity.freeDeliveryPrice,
        )
    }

    fun toEntity(model: DeliveryInfoModel): DeliveryInfoEntity {
        return DeliveryInfoEntity(
            deliveryPrice = model.deliveryPrice,
            freeDeliveryPrice = model.freeDeliveryPrice,
        )
    }
}