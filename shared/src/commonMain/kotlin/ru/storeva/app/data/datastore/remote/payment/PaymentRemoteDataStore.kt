package ru.storeva.app.data.datastore.remote.payment

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.data.entities.PaymentEntity
import ru.storeva.app.data.entities.PaymentTypeEntity

interface PaymentRemoteDataStore {
    fun getPaymentTypes(): Flow<List<PaymentTypeEntity>>

    fun create(
        paymentType: String,
        amount: Double,
        cryptogram: String?,
        token: String?,
    ): Flow<PaymentEntity>
}