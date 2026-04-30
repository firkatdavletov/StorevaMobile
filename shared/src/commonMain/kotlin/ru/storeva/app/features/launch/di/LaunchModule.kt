package ru.storeva.app.features.launch.di

import io.ktor.client.HttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.storeva.app.di.factory.launch.DefaultLaunchComponentFactory
import ru.storeva.app.di.factory.launch.LaunchComponentFactory
import ru.storeva.app.features.launch.data.mapper.AppConfigMapper
import ru.storeva.app.features.launch.data.network.api.AppConfigApi
import ru.storeva.app.features.launch.data.network.api.AppConfigApiImpl
import ru.storeva.app.features.launch.data.repository.AppConfigRepositoryImpl
import ru.storeva.app.features.launch.domain.repository.AppConfigRepository
import ru.storeva.app.features.launch.domain.usecase.LoadLaunchDataUseCase

val launchModule =
    module {
        single<AppConfigApi> {
            val httpClient = get<HttpClient>(named("no_auth"))
            AppConfigApiImpl(httpClient)
        }
        single<AppConfigRepository> {
            AppConfigRepositoryImpl(
                appConfigApi = get(),
                mapper = get(),
            )
        }
        factory {
            LoadLaunchDataUseCase(
                appConfigRepository = get(),
                dispatchers = get(),
            )
        }
        factory { AppConfigMapper() }
        factory<LaunchComponentFactory> {
            DefaultLaunchComponentFactory(
                dispatchers = get(),
                snackBarManager = get(),
                loadLaunchDataUseCase = get(),
                errorHandler = get(),
            )
        }
    }