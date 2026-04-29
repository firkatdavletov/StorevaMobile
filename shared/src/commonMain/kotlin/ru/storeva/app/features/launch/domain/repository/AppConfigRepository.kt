package ru.storeva.app.features.launch.domain.repository

import ru.storeva.app.features.launch.domain.model.AppConfig

interface AppConfigRepository {
    suspend fun appConfig(): AppConfig
}