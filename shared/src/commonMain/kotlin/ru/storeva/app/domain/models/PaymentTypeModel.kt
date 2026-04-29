package ru.storeva.app.domain.models

data class PaymentTypeModel(
    val id: String,
    val title: String,
    val selected: Boolean,
)