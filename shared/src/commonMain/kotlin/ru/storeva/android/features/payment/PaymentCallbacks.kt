package ru.storeva.android.features.payment

data class PaymentCallbacks(
    val navigateBack: () -> Unit,
    val navigateToOrder: (orderId: Long) -> Unit,
    val navigateToMap: () -> Unit,
)