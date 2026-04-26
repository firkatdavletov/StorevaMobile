package ru.storeva.android.data.api.payment_api

import ru.storeva.android.data.api.payment_api.model.GetBanksResponse
import ru.storeva.android.data.api.payment_api.model.GetPaymentTypesResponseBody
import ru.storeva.android.data.api.payment_api.model.PayOrderRequestBody
import ru.storeva.android.data.entities.PaymentEntity

interface PaymentApi {

    suspend fun getPaymentTypes(): GetPaymentTypesResponseBody

    suspend fun create(body: PayOrderRequestBody): PaymentEntity

    suspend fun getQrBanks(): GetBanksResponse

    suspend fun getSubBanks(): GetBanksResponse
}