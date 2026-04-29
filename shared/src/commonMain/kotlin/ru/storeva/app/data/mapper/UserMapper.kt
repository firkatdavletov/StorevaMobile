package ru.storeva.app.data.mapper

import ru.storeva.app.data.entities.UserEntity
import ru.storeva.app.domain.models.UserModel

class UserMapper {
    fun toModel(entity: UserEntity) =
        UserModel(
            name = entity.name,
            phone = entity.phone,
            email = entity.email,
            company = entity.company,
        )

    fun toEntity(model: UserModel) =
        UserEntity(
            phone = model.phone,
            name = model.name,
            email = model.email,
            company = model.company,
        )
}