package ru.storeva.app.features.authorization

data class AuthNavCallbacks(
    val navigateToHome: () -> Unit,
    val navigateToPayment: () -> Unit,
)