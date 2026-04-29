package ru.storeva.app.data.repositories.payment

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.storeva.app.data.datastore.remote.payment.PaymentRemoteDataStore
import ru.storeva.app.data.mapper.PaymentMapper
import ru.storeva.app.domain.models.PaymentModel
import ru.storeva.app.domain.models.PaymentTypeModel
import ru.storeva.app.domain.repositories.PaymentRepository

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