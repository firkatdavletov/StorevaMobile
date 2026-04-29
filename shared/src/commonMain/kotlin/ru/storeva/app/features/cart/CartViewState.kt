package ru.storeva.app.features.cart

import ru.storeva.app.domain.models.CartItemModel
import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.features.base.Reducer

data class CartViewState(
    val totalPrice: Long,
    val deliveryPrice: Long,
    val productsPrice: Long,
    val freeDeliveryPrice: Long?,
    val cartItems: List<CartItemModel>,
    val addressString: String,
    val deliveryType: DeliveryType,
    val continueText: String,
) : Reducer.ViewState