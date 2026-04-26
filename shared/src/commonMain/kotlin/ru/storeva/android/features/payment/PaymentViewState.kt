package ru.storeva.android.features.payment

import ru.storeva.android.domain.models.DeliveryType
import ru.storeva.android.domain.models.PaymentTypeModel
import ru.storeva.android.features.base.Reducer

data class PaymentViewState(
    val isLoading: Boolean,
    val deliveryType: DeliveryType,
    val addressString: String?,
    val departmentName: String?,
    val isPrivateHome: Boolean,
    val entrance: String,
    val flat: String,
    val comment: String,
    val productPrice: Long,
    val deliveryPrice: Long,
    val totalAmount: Long,
    val paymentTypes: List<PaymentTypeModel>,
    val entranceInputError: String? = null,
    val flatInputError: String? = null,
    val storeIsClosed: Boolean,
) : Reducer.ViewState