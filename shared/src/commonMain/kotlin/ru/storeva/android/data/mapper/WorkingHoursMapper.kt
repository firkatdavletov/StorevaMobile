package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.WorkingHourEntity
import ru.storeva.android.domain.models.WorkingHoursModel

class WorkingHoursMapper {
    fun toModel(entity: WorkingHourEntity) =
        WorkingHoursModel(
            dayWeek = entity.dayOfWeek,
            openTime = entity.openTime,
            closeTime = entity.closeTime,
        )

    fun toModel(entities: List<WorkingHourEntity>) = entities.map { toModel(it) }
}