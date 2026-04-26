package ru.storeva.android.features.dialogs.delete_user_dialog

import com.arkivanov.decompose.ComponentContext
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.base.BaseComponent

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