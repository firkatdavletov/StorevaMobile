package ru.storeva.app.features.dialogs.delete_user_dialog

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.base.BaseComponent

abstract class DeleteUserComponent(
    componentContext: ComponentContext,
    initialState: DeleteUserDialogViewState,
    reducer: DeleteUserDialogReducer,
    snackBarManager: SnackBarManager,
) : BaseComponent<DeleteUserDialogViewState, DeleteUserDialogViewEvent, DeleteUserDialogViewEffect>(
        componentContext,
        initialState,
        reducer,
        snackBarManager,
    )