package ru.storeva.android.data.api.payment_api.model

import kotlinx.serialization.Serializable
import ru.storeva.android.data.entities.PaymentTypeEntity

@Serializable
data class GetPaymentTypesResponseBody(
    val paymentTypes: List<PaymentTypeEntity>,
)