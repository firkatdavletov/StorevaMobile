package ru.storeva.app.domain.models

data class PaymentModel(
    val success: Boolean,
    val qrLink: String?,
)