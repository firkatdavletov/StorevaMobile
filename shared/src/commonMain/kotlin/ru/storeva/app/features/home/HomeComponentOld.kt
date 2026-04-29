package ru.storeva.app.features.home

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.base.BaseComponent

@Deprecated("use HomeComponent")
abstract class HomeComponentOld(
    componentContext: ComponentContext,
    snackBarManager: SnackBarManager,
    initialState: HomeViewState,
    reducer: HomeReducer,
) : BaseComponent<HomeViewState, HomeViewEvent, HomeViewEffect>(
        componentContext = componentContext,
        initialState = initialState,
        reducer = reducer,
        snackBarManager = snackBarManager,
    )