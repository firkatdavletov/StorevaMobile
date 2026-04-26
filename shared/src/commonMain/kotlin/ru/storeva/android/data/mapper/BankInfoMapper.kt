package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.BankInfoEntity
import ru.storeva.android.domain.models.BankInfoModel

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