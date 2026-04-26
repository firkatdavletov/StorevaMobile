package ru.storeva.android.features.cart

import ru.storeva.android.domain.models.CartItemModel
import ru.storeva.android.domain.models.CartModel
import ru.storeva.android.features.base.Reducer

sealed interface CartViewEvent : Reducer.ViewEvent {
    data object OnBackClick : CartViewEvent

    data object OnConfirmButtonClicked : CartViewEvent

    data class OnCartLoaded(val cartModel: CartModel) : CartViewEvent

    data class OnAddToCart(val product: CartItemModel) : CartViewEvent

    data class OnRemoveFromCart(val product: CartItemModel) : CartViewEvent

    data class OnError(val message: String?) : CartViewEvent

    data class OnThrowError(val throwable: Throwable) : CartViewEvent
}