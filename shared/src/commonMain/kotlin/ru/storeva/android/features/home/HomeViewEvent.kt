package ru.storeva.android.features.home

import ru.storeva.android.domain.models.CartItemModel
import ru.storeva.android.domain.models.CartModel
import ru.storeva.android.domain.models.CategoryModel
import ru.storeva.android.domain.models.OrderModel
import ru.storeva.android.domain.models.ProductModel
import ru.storeva.android.domain.models.UserModel
import ru.storeva.android.features.base.Reducer

sealed interface HomeViewEvent : Reducer.ViewEvent {
    data object OnAddressClicked : HomeViewEvent

    data object OnCartButtonClicked : HomeViewEvent

    data object OnProfileClicked : HomeViewEvent

    data class OnCategoriesLoaded(
        val categories: List<CategoryModel>,
        val cartItems: List<CartItemModel>,
    ) : HomeViewEvent

    data class OnCategoryClicked(val categoryId: Long, val categoryTitle: String) : HomeViewEvent

    data class OnCartLoaded(val cartModel: CartModel) : HomeViewEvent

    data class OnError(val text: String?) : HomeViewEvent

    data class OnThrowError(val throwable: Throwable) : HomeViewEvent

    data class OnAddToCart(val product: ProductModel) : HomeViewEvent

    data class OnRemoveFromCart(val product: ProductModel) : HomeViewEvent

    data class OnCurrentOrderLoaded(val orders: List<OrderModel>) : HomeViewEvent

    data class OnOrderClicked(val id: Long) : HomeViewEvent

    data class OnUserLoaded(val userModel: UserModel?) : HomeViewEvent

    data class OnShowDetails(val product: ProductModel) : HomeViewEvent
}