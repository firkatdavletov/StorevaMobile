package ru.storeva.app.features.launch.di

import com.arkivanov.decompose.ComponentContext
import io.ktor.client.HttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.storeva.app.features.launch.data.mapper.AppConfigMapper
import ru.storeva.app.features.launch.data.network.api.AppConfigApi
import ru.storeva.app.features.launch.data.network.api.AppConfigApiImpl
import ru.storeva.app.features.launch.data.repository.AppConfigRepositoryImpl
import ru.storeva.app.features.launch.domain.repository.AppConfigRepository
import ru.storeva.app.features.launch.domain.usecase.LoadLaunchDataUseCase
import ru.storeva.app.features.launch.presentation.DefaultLaunchComponent
import ru.storeva.app.features.launch.presentation.LaunchComponent
import ru.storeva.app.features.launch.presentation.LaunchNavigationCallbacks

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
        factory<LaunchComponent> { (componentContext: ComponentContext, callbacks: LaunchNavigationCallbacks) ->
            DefaultLaunchComponent(
                componentContext = componentContext,
                callbacks = callbacks,
                snackBarManager = get(),
                loadLaunchDataUseCase = get(),
                dispatchers = get(),
                errorHandler = get(),
            )
        }
    }