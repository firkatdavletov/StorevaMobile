package ru.storeva.android.domain.models

data class VerifyPhoneNumberModel(
    val success: Boolean,
    val checkId: String?,
    val callPhone: String?,
)