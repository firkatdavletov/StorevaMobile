package ru.storeva.app.data.mapper

import ru.storeva.app.data.entities.TokenPairEntity
import ru.storeva.app.domain.models.TokenPairModel

class TokenPairMapper {
    fun toModel(entity: TokenPairEntity) = TokenPairModel(entity.access, entity.refresh)
}