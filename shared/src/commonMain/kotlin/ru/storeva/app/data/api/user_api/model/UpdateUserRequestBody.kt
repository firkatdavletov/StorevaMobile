package ru.storeva.app.data.api.user_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.entities.UserEntity

@Serializable
data class UpdateUserRequestBody(
    val user: UserEntity,
)