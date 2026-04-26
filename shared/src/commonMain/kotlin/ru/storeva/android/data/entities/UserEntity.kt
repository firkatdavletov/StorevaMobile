package ru.storeva.android.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val phone: String,
    val name: String,
    val email: String,
    val company: String?,
)