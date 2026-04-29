package ru.storeva.app.domain.models

data class UserModel(
    val name: String,
    val phone: String,
    val email: String,
    val company: String?,
)