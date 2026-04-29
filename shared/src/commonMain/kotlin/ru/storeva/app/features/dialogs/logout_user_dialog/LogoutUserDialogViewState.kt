package ru.storeva.app.features.dialogs.logout_user_dialog

import ru.storeva.app.features.base.Reducer

data class LogoutUserDialogViewState(
    val isLoading: Boolean,
) : Reducer.ViewState