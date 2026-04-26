package ru.storeva.android.data.datastore.remote.payment

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.storeva.android.data.api.payment_api.PaymentApi
import ru.storeva.android.data.api.payment_api.model.PayOrderRequestBody
import ru.storeva.android.data.entities.PaymentEntity
import ru.storeva.android.data.entities.PaymentTypeEntity

class DefaultPaymentRemoteDataStore(
    private val paymentApi: PaymentApi,
) : PaymentRemoteDataStore {
    override fun getPaymentTypes(): Flow<List<PaymentTypeEntity>> {
        return flow {
            emit(paymentApi.getPaymentTypes())
        }.map { it.paymentTypes }
    }

    override fun create(
        paymentType: String,
        amount: Double,
        cryptogram: String?,
        token: String?,
    ): Flow<PaymentEntity> {
        val request = PayOrderRequestBody(
            paymentType = paymentType,
            amount = amount,
            token = token,
            cryptogram = cryptogram,
        )
        return flow {
            emit(paymentApi.create(request))
        }
    }
}