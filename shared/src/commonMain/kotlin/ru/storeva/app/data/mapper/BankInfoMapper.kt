package ru.storeva.app.data.mapper

import ru.storeva.app.data.entities.BankInfoEntity
import ru.storeva.app.domain.models.BankInfoModel

class BankInfoMapper {

    fun toModel(entity: BankInfoEntity) =
        BankInfoModel(
            bankName = entity.bankName,
            logoUrl = entity.logoUrl,
            schema = entity.schema,
            packageName = entity.packageName,
        )

    fun toModel(entities: List<BankInfoEntity>) = entities.map { toModel(it) }
}