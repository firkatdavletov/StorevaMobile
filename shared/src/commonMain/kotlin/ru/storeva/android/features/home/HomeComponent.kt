package ru.storeva.android.features.home

import com.arkivanov.decompose.ComponentContext
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.base.BaseComponent

abstract class HomeComponent(
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