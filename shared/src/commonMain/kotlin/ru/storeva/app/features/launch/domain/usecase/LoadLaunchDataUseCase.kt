package ru.storeva.app.features.launch.domain.usecase

import ru.storeva.app.core.coroutine.AppDispatchers
import ru.storeva.app.core.usecase.SuspendUseCase
import ru.storeva.app.features.launch.domain.model.AppConfig
import ru.storeva.app.features.launch.domain.repository.AppConfigRepository

class LoadLaunchDataUseCase(
    private val appConfigRepository: AppConfigRepository,
    dispatchers: AppDispatchers,
) : SuspendUseCase<Unit, AppConfig>(dispatchers.io) {
    override suspend fun execute(param: Unit): AppConfig {
        return appConfigRepository.appConfig()
    }
}