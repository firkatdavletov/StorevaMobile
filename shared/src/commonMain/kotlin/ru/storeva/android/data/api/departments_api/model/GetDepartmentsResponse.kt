package ru.storeva.android.data.api.departments_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.entities.DepartmentEntity

@Serializable
data class GetDepartmentsResponse(
    val departments: List<DepartmentEntity>,
)