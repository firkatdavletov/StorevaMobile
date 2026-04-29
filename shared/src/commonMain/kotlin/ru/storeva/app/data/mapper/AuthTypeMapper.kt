package ru.storeva.app.data.mapper

import ru.storeva.app.data.entities.AuthTypeEntity
import ru.storeva.app.domain.models.AuthTypeModel

class AuthTypeMapper {
    fun toModel(entity: AuthTypeEntity) =
        AuthTypeModel(
            key = entity.key,
            title = entity.title,
        )

    fun toModel(entities: List<AuthTypeEntity>) = entities.map { toModel(it) }
}