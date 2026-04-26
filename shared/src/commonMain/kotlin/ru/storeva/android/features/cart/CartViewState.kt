package ru.storeva.android.features.cart

import ru.storeva.android.domain.models.CartItemModel
import ru.storeva.android.domain.models.DeliveryType
import ru.storeva.android.features.base.Reducer

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