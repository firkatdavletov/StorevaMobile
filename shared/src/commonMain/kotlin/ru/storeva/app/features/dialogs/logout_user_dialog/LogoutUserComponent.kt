package ru.storeva.app.features.dialogs.logout_user_dialog

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.base.BaseComponent

abstract class LogoutUserComponent(
    componentContext: ComponentContext,
    initialState: LogoutUserDialogViewState,
    reducer: LogoutUserDialogReducer,
    snackBarManager: SnackBarManager,
) : BaseComponent<LogoutUserDialogViewState, LogoutUserDialogViewEvent, LogoutUserDialogViewEffect>(
        componentContext,
        initialState,
        reducer,
        snackBarManager,
    )