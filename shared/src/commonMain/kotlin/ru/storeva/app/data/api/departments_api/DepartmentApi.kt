package ru.storeva.app.data.api.departments_api

import ru.storeva.app.data.api.departments_api.model.GetDepartmentsResponse

interface DepartmentApi {
    suspend fun getDepartments(): GetDepartmentsResponse
}