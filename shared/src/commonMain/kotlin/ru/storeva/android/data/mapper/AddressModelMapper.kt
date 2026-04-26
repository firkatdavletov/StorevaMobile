package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.AddressEntity
import ru.storeva.android.domain.models.AddressModel

class AddressModelMapper(
    private val cityMapper: CityMapper,
) {
    fun toModel(entity: AddressEntity) =
        AddressModel(
            street = entity.street,
            house = entity.house,
            entrance = entity.entrance,
            flat = entity.flat,
            intercome = entity.intercome,
            comment = entity.comment,
            city = cityMapper.toModel(entity.city),
            latitude = entity.latitude,
            longitude = entity.longitude,
        )

    fun toEntity(model: AddressModel): AddressEntity =
        AddressEntity(
            street = model.street,
            house = model.house,
            entrance = model.entrance,
            flat = model.flat,
            intercome = model.intercome,
            comment = model.comment,
            city = cityMapper.toEntity(model.city),
            latitude = model.latitude,
            longitude = model.longitude,
        )
}