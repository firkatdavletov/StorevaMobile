package ru.storeva.android.data.mapper

import ru.storeva.android.data.entities.PaymentEntity
import ru.storeva.android.data.entities.PaymentTypeEntity
import ru.storeva.android.domain.models.PaymentModel
import ru.storeva.android.domain.models.PaymentTypeModel

class PaymentMapper(
    private val bankInfoMapper: BankInfoMapper,
) {
    fun toModel(entity: PaymentTypeEntity) =
        PaymentTypeModel(
            id = entity.key,
            title = entity.title,
            selected = false,
        )

    fun toModel(entities: List<PaymentTypeEntity>) = entities.map { toModel(it) }

    fun toModel(entity: PaymentEntity) =
        PaymentModel(
            entity.success,
            entity.model?.qrUrl,
        )
}