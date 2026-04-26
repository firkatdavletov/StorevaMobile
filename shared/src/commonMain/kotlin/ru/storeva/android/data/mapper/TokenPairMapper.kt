package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.TokenPairEntity
import ru.storeva.android.domain.models.TokenPairModel

class TokenPairMapper {
    fun toModel(entity: TokenPairEntity) = TokenPairModel(entity.access, entity.refresh)
}