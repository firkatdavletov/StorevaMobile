package ru.storeva.app.features.launch.data.repository

import kotlinx.coroutines.flow.flow
import ru.storeva.app.features.launch.data.mapper.AppConfigMapper
import ru.storeva.app.features.launch.data.network.api.AppConfigApi
import ru.storeva.app.features.launch.domain.model.AppConfig
import ru.storeva.app.features.launch.domain.repository.AppConfigRepository

class AppConfigRepositoryImpl(
    private val appConfigApi: AppConfigApi,
    private val mapper: AppConfigMapper,
) : AppConfigRepository {
    override suspend fun appConfig(): AppConfig {
        val appConfigDto = appConfigApi.appConfig()
        return mapper.toModel(appConfigDto)
    }
}