package ru.storeva.app.features.dialogs.delete_user_dialog

data class DeleteUserDialogCallbacks(
    val onDismiss: () -> Unit,
    val onSuccess: () -> Unit,
)