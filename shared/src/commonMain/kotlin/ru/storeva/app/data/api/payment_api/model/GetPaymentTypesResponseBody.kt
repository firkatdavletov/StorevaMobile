package ru.storeva.app.data.api.payment_api.model

import kotlinx.serialization.Serializable
import ru.storeva.app.data.entities.PaymentTypeEntity

@Serializable
data class GetPaymentTypesResponseBody(
    val paymentTypes: List<PaymentTypeEntity>,
)