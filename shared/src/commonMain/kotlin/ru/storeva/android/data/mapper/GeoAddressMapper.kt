package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.GeoAddressEntity
import ru.storeva.android.domain.models.GeoAddressModel

class GeoAddressMapper(
    private val cityMapper: CityMapper,
    private val deliveryInfoMapper: DeliveryInfoMapper,
) {
    fun toModel(entity: GeoAddressEntity) =
        GeoAddressModel(
            city = cityMapper.toModel(entity.city),
            street = entity.street,
            house = entity.house,
            entrance = entity.entrance,
            deliveryInfo = entity.deliveryInfo?.let { deliveryInfoMapper.toModel(it) },
            deliveryTime = entity.deliveryTime,
            latitude = entity.latitude,
            longitude = entity.longitude,
            uri = entity.uri,
        )

    fun toModel(entities: List<GeoAddressEntity>) = entities.map { toModel(it) }
}