package ru.storeva.app.data.api.departments_api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.storeva.app.data.api.departments_api.model.GetDepartmentsResponse

class DepartmentApiImpl(private val httpClient: HttpClient) : DepartmentApi {
    override suspend fun getDepartments(): GetDepartmentsResponse {
        return httpClient.get("departments").body()
    }
}