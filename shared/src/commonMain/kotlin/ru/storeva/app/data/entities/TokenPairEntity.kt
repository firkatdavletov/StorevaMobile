package ru.storeva.app.data.entities

import kotlinx.serialization.Serializable

@Serializable
class TokenPairEntity(
    val access: String,
    val refresh: String,
)