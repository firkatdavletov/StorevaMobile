package ru.storeva.android.features.dialogs.logout_user_dialog

import ru.storeva.android.features.base.Reducer

data class LogoutUserDialogViewState(
    val isLoading: Boolean,
) : Reducer.ViewState