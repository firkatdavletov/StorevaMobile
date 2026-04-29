package ru.storeva.app.data.api.departments_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.entities.DepartmentEntity

@Serializable
data class GetDepartmentsResponse(
    val departments: List<DepartmentEntity>,
)