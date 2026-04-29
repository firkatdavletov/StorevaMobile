package ru.storeva.app.features.authorization.verification_component

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.base.BaseComponent

abstract class VerificationComponent(
    componentContext: ComponentContext,
    initialState: VerifyViewState,
    snackBarManager: SnackBarManager,
) : BaseComponent<VerifyViewState, VerifyViewEvent, VerifyViewEffect>(
        reducer = VerifyReducer(),
        initialState = initialState,
        componentContext = componentContext,
        snackBarManager = snackBarManager,
    )