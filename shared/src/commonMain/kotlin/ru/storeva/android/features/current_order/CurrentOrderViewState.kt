package ru.storeva.android.features.current_order

import ru.storeva.android.domain.models.DeliveryType
import ru.storeva.android.domain.models.OrderItemModel
import ru.storeva.android.features.base.Reducer

data class CurrentOrderViewState(
    val number: String,
    val deliveryType: DeliveryType,
    val addressString: String,
    val status: String,
    val items: List<OrderItemModel>,
    val deliveryPrice: Long,
    val totalAmount: Long,
    val productsPrice: Long,
    val comment: String,
) : Reducer.ViewState