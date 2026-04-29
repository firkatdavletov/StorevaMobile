package ru.storeva.app.features.dialogs.logout_user_dialog

data class LogoutUserDialogCallbacks(
    val onDismiss: () -> Unit,
    val onSuccess: () -> Unit,
)