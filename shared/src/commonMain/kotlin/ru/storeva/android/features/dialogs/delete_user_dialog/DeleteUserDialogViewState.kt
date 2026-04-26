package ru.storeva.android.features.dialogs.delete_user_dialog

import ru.storeva.android.features.base.Reducer

data class DeleteUserDialogViewState(
    val isLoading: Boolean,
) : Reducer.ViewState