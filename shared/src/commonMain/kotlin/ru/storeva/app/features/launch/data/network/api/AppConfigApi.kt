package ru.storeva.app.features.launch.data.network.api

import ru.storeva.app.features.launch.data.network.dto.AppConfigDto

interface AppConfigApi {
    suspend fun appConfig(): AppConfigDto
}