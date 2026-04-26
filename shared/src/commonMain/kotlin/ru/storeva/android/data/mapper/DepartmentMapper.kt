package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.DepartmentEntity
import ru.storeva.android.domain.models.DepartmentModel

class DepartmentMapper(
    private val cityMapper: CityMapper,
    private val workingHoursMapper: WorkingHoursMapper,
) {
    fun toModel(entity: DepartmentEntity): DepartmentModel =
        DepartmentModel(
            id = entity.id,
            name = entity.name,
            latitude = entity.latitude,
            longitude = entity.longitude,
            city = cityMapper.toModel(entity.city),
            currentWorkingHours = entity.currentWorkingHours?.let { workingHoursMapper.toModel(it) },
            isWorkingNow = entity.isWorkingNow,
        )

    fun toModel(entities: List<DepartmentEntity>): List<DepartmentModel> = entities.map { toModel(it) }
}