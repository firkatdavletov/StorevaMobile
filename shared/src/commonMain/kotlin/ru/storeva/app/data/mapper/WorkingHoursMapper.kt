package ru.storeva.app.data.mapper

import ru.storeva.app.data.entities.WorkingHourEntity
import ru.storeva.app.domain.models.WorkingHoursModel

class WorkingHoursMapper {
    fun toModel(entity: WorkingHourEntity) =
        WorkingHoursModel(
            dayWeek = entity.dayOfWeek,
            openTime = entity.openTime,
            closeTime = entity.closeTime,
        )

    fun toModel(entities: List<WorkingHourEntity>) = entities.map { toModel(it) }
}