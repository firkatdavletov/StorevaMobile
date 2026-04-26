package ru.storeva.android.data.entities

import kotlinx.serialization.Serializable

@Serializable
class TokenPairEntity(
    val access: String,
    val refresh: String,
)