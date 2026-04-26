package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.AuthTypeEntity
import ru.storeva.android.domain.models.AuthTypeModel

class AuthTypeMapper {
    fun toModel(entity: AuthTypeEntity) =
        AuthTypeModel(
            key = entity.key,
            title = entity.title,
        )

    fun toModel(entities: List<AuthTypeEntity>) = entities.map { toModel(it) }
}