package ru.storeva.app.features.dialogs.delete_user_dialog

import ru.storeva.app.features.base.Reducer

data class DeleteUserDialogViewState(
    val isLoading: Boolean,
) : Reducer.ViewState