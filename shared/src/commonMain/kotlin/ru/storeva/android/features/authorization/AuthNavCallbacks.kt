package ru.storeva.android.features.authorization

data class AuthNavCallbacks(
    val navigateToHome: () -> Unit,
    val navigateToPayment: () -> Unit,
)