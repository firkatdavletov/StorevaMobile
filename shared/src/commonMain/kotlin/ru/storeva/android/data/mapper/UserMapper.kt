package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.UserEntity
import ru.storeva.android.domain.models.UserModel

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