package ru.storeva.android.features.dialogs.logout_user_dialog

import com.arkivanov.decompose.ComponentContext
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.base.BaseComponent

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