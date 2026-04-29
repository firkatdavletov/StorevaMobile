package ru.storeva.app.features.dialogs.product_card

import ru.storeva.app.domain.models.CartModel
import ru.storeva.app.domain.models.ProductModel
import ru.storeva.app.features.base.Reducer

sealed interface ProductCardViewEvent : Reducer.ViewEvent {
    data class OnProductLoaded(val product: ProductModel) : ProductCardViewEvent

    data class OnError(val error: String) : ProductCardViewEvent

    data class OnThrowError(val throwable: Throwable) : ProductCardViewEvent

    data class OnCartLoaded(val cart: CartModel) : ProductCardViewEvent

    data object OnAddToCart : ProductCardViewEvent

    data object OnRemoveFromCart : ProductCardViewEvent
}