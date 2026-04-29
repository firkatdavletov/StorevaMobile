package ru.storeva.app.features.dialogs.delete_user_dialog

import ru.storeva.app.features.base.Reducer

sealed interface DeleteUserDialogViewEvent : Reducer.ViewEvent {
    data object OnConfirm : DeleteUserDialogViewEvent

    data object OnDismiss : DeleteUserDialogViewEvent

    data class OnError(val error: String) : DeleteUserDialogViewEvent

    data class OnThrowError(val throwable: Throwable) : DeleteUserDialogViewEvent

    data object OnLoading : DeleteUserDialogViewEvent
}