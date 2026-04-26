package ru.storeva.android.domain.models

data class PaymentModel(
    val success: Boolean,
    val qrLink: String?,
)