package ru.storeva.app.features.launch.data.mapper

import ru.storeva.app.features.launch.data.network.dto.AppConfigDto
import ru.storeva.app.features.launch.domain.model.AppConfig

class AppConfigMapper {
    fun toModel(dto: AppConfigDto): AppConfig {
        return AppConfig(
            minSupportedVersion = dto.minSupportedVersion,
            maintenance = dto.maintenance,
            featureFlags = dto.featureFlags,
        )
    }
}