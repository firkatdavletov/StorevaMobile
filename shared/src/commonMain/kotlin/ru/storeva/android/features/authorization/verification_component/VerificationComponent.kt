package ru.storeva.android.features.authorization.verification_component

import com.arkivanov.decompose.ComponentContext
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.base.BaseComponent

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