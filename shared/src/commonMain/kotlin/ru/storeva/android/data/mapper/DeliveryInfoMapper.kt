package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.DeliveryInfoEntity
import ru.storeva.android.domain.models.DeliveryInfoModel

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