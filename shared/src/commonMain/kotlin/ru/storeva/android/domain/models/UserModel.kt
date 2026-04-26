package ru.storeva.android.domain.models

data class UserModel(
    val name: String,
    val phone: String,
    val email: String,
    val company: String?,
)