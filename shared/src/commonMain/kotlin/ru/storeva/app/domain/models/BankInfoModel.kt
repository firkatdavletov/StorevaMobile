package ru.storeva.app.domain.models

data class BankInfoModel(
    val bankName: String,
    val logoUrl: String,
    val schema: String,
    val packageName: String? = null,
)