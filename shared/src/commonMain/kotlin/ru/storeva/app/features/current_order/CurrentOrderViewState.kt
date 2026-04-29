package ru.storeva.app.features.current_order

import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.domain.models.OrderItemModel
import ru.storeva.app.features.base.Reducer

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