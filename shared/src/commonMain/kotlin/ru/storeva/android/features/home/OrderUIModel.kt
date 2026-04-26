package ru.storeva.android.features.home

data class OrderUIModel(
    val id: Long,
    val number: String,
    val status: String,
    val amount: Long,
)