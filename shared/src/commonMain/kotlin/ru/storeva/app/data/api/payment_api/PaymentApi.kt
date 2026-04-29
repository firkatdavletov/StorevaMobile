package ru.storeva.app.data.api.payment_api

import ru.storeva.app.data.api.payment_api.model.GetBanksResponse
import ru.storeva.app.data.api.payment_api.model.GetPaymentTypesResponseBody
import ru.storeva.app.data.api.payment_api.model.PayOrderRequestBody
import ru.storeva.app.data.entities.PaymentEntity

interface PaymentApi {

    suspend fun getPaymentTypes(): GetPaymentTypesResponseBody

    suspend fun create(body: PayOrderRequestBody): PaymentEntity

    suspend fun getQrBanks(): GetBanksResponse

    suspend fun getSubBanks(): GetBanksResponse
}