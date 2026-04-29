package ru.storeva.app.features.search_address

data class SearchAddressCallbacks(
    val navigateBack: () -> Unit,
    val navigateToHome: () -> Unit,
    val navigateToPayment: () -> Unit,
    val navigateToMap: (fromScreen: String?) -> Unit,
)