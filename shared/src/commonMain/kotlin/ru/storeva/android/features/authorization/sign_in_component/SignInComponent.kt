package ru.storeva.android.features.authorization.sign_in_component

import com.arkivanov.decompose.ComponentContext
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.base.BaseComponent

abstract class SignInComponent(componentContext: ComponentContext, snackBarManager: SnackBarManager) :
    BaseComponent<SignInViewState, SignInViewEvent, SignViewEffect>(
        componentContext = componentContext,
        initialState = SignInViewState(
            authTypes = emptyList(),
            isLoading = true,
            isError = false,
            selectedAuthType = "",
            phoneNumber = "",
            confirmEnabled = false,
        ),
        reducer = SignInReducer(),
        snackBarManager = snackBarManager,
    )