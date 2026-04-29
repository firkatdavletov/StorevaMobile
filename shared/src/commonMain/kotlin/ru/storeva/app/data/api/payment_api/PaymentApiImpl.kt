package ru.storeva.app.data.api.payment_api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.storeva.app.data.api.payment_api.model.GetBanksResponse
import ru.storeva.app.data.api.payment_api.model.GetPaymentTypesResponseBody
import ru.storeva.app.data.api.payment_api.model.PayOrderRequestBody
import ru.storeva.app.data.entities.PaymentEntity

class PaymentApiImpl(private val httpClient: HttpClient) : PaymentApi {
    override suspend fun getPaymentTypes(): GetPaymentTypesResponseBody {
        return httpClient.get("payment/paymentTypes").body()
    }

    override suspend fun create(body: PayOrderRequestBody): PaymentEntity {
        return httpClient
            .post("payment/payOrder") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }.body()
    }

    override suspend fun getQrBanks(): GetBanksResponse {
        return httpClient.get("payment/getQrBanks").body()
    }

    override suspend fun getSubBanks(): GetBanksResponse {
        return httpClient.get("payment/getSubBanks").body()
    }
}