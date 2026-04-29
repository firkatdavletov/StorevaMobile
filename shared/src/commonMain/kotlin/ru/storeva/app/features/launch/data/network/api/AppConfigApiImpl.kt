package ru.storeva.app.features.launch.data.network.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import ru.storeva.app.features.launch.data.network.dto.AppConfigDto

class AppConfigApiImpl(
    private val httpClient: HttpClient,
) : AppConfigApi {
    override suspend fun appConfig(): AppConfigDto {
        return httpClient.get("api/v1/app-config").body()
    }
}