package ru.storeva.android.data.repositories.payment

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.storeva.android.data.datastore.remote.payment.PaymentRemoteDataStore
import ru.storeva.android.data.mapper.PaymentMapper
import ru.storeva.android.domain.models.PaymentModel
import ru.storeva.android.domain.models.PaymentTypeModel
import ru.storeva.android.domain.repositories.PaymentRepository

class DefaultPaymentRepository(
    private val paymentRemoteDataStore: PaymentRemoteDataStore,
    private val paymentMapper: PaymentMapper,
) : PaymentRepository {
    override fun getPaymentTypes(): Flow<List<PaymentTypeModel>> {
        return paymentRemoteDataStore.getPaymentTypes().map { paymentMapper.toModel(it) }
    }

    override fun create(
        paymentType: String,
        amount: Double,
        cryptogram: String?,
        token: String?,
    ): Flow<PaymentModel> {
        return paymentRemoteDataStore
            .create(paymentType, amount, cryptogram, token)
            .map { paymentMapper.toModel(it) }
    }
}