package ru.storeva.android.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.PaymentModel
import ru.storeva.android.domain.models.PaymentTypeModel

interface PaymentRepository {
    fun getPaymentTypes(): Flow<List<PaymentTypeModel>>

    fun create(
        paymentType: String,
        amount: Double,
        cryptogram: String?,
        token: String?,
    ): Flow<PaymentModel>
}