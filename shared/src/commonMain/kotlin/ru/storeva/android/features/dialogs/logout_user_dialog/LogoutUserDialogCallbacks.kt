package ru.storeva.android.features.dialogs.logout_user_dialog

data class LogoutUserDialogCallbacks(
    val onDismiss: () -> Unit,
    val onSuccess: () -> Unit,
)