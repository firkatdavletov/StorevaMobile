package ru.storeva.android.features.launch

data class LaunchNavigationCallbacks(
    val navigateToHome: () -> Unit,
    val navigateToSelectAddress: () -> Unit,
)