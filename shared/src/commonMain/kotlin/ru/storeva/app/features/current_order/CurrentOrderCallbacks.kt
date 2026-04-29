package ru.storeva.app.features.current_order

data class CurrentOrderCallbacks(
    val navigateToBack: () -> Unit,
    val navigateToHome: () -> Unit,
)