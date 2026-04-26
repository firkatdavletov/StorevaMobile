package ru.storeva.android.data.api.departments_api

import ru.storeva.android.data.api.departments_api.model.GetDepartmentsResponse

interface DepartmentApi {
    suspend fun getDepartments(): GetDepartmentsResponse
}