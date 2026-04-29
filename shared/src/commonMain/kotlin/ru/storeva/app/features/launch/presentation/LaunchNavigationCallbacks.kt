package ru.storeva.app.features.launch.presentation

data class LaunchNavigationCallbacks(
    val navigateToHome: () -> Unit,
    val navigateToSelectAddress: () -> Unit,
)