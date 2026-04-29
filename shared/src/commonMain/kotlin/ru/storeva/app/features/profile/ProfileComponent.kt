package ru.storeva.app.features.profile

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.base.BaseComponent

abstract class ProfileComponent(
    componentContext: ComponentContext,
    initialState: ProfileViewState,
    reducer: ProfileViewReducer,
    snackBarManager: SnackBarManager,
) : BaseComponent<ProfileViewState, ProfileViewEvent, ProfileViewEffect>(
        componentContext = componentContext,
        initialState = initialState,
        reducer = reducer,
        snackBarManager = snackBarManager,
    )