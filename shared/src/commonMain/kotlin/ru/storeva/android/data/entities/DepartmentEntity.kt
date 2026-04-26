package ru.storeva.android.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class DepartmentEntity(
    val id: Long,
    val name: String,
    val city: CityEntity,
    val latitude: Double,
    val longitude: Double,
    val currentWorkingHours: WorkingHourEntity?,
    val isWorkingNow: Boolean,
)