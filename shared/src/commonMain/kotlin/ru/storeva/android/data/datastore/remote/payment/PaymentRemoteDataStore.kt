package ru.storeva.android.data.datastore.remote.payment

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.data.entities.PaymentEntity
import ru.storeva.android.data.entities.PaymentTypeEntity

interface PaymentRemoteDataStore {
    fun getPaymentTypes(): Flow<List<PaymentTypeEntity>>

    fun create(
        paymentType: String,
        amount: Double,
        cryptogram: String?,
        token: String?,
    ): Flow<PaymentEntity>
}