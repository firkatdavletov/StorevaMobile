package ru.storeva.app.features.profile

data class ProfileCallbacks(
    val navigateBack: () -> Unit,
    val showDeleteUserDialog: () -> Unit,
    val showLogoutUserDialog: () -> Unit,
)