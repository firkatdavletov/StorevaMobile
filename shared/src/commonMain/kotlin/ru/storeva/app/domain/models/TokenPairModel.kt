package ru.storeva.app.domain.models

data class TokenPairModel(
    val access: String,
    val refresh: String,
)