package ru.storeva.android.data.api.user_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.entities.UserEntity

@Serializable
data class UpdateUserRequestBody(
    val user: UserEntity,
)