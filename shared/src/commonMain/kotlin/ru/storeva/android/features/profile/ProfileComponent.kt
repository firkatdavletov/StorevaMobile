package ru.storeva.android.features.profile

import com.arkivanov.decompose.ComponentContext
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.base.BaseComponent

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