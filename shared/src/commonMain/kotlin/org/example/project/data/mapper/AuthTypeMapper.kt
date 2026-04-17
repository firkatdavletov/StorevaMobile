package org.example.project.data.mapper

import org.example.project.data.entities.AuthTypeEntity
import org.example.project.domain.models.AuthTypeModel

class AuthTypeMapper {
    fun toModel(entity: AuthTypeEntity) =
        AuthTypeModel(
            key = entity.key,
            title = entity.title,
        )

    fun toModel(entities: List<AuthTypeEntity>) = entities.map { toModel(it) }
}