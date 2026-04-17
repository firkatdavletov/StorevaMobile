package org.example.project.features.current_order

import org.example.project.domain.models.DeliveryType
import org.example.project.domain.models.OrderItemModel
import org.example.project.domain.models.OrderStatus
import org.example.project.features.base.Reducer

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