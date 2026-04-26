package ru.storeva.android.features.catalog

import ru.storeva.android.domain.models.CartModel
import ru.storeva.android.domain.models.CategoryModel
import ru.storeva.android.domain.models.ProductModel
import ru.storeva.android.domain.models.UserModel
import ru.storeva.android.features.base.Reducer

sealed interface CatalogViewEvent : Reducer.ViewEvent {
    data object OnAddressClicked : CatalogViewEvent

    data class OnUserLoaded(val user: UserModel) : CatalogViewEvent

    data class OnProductsLoaded(val products: List<ProductModel>) : CatalogViewEvent

    data class OnCartLoaded(val cartModel: CartModel) : CatalogViewEvent

    data class OnCategoryClicked(val categoryId: Long) : CatalogViewEvent

    data class OnCategoryLoaded(val category: CategoryModel) : CatalogViewEvent

    data object OnBackClicked : CatalogViewEvent

    data class OnAddToCart(val product: ProductModel) : CatalogViewEvent

    data class OnRemoveFromCart(val product: ProductModel) : CatalogViewEvent

    data object OnCartButtonClicked : CatalogViewEvent

    data class OnProductCardClicked(val product: ProductModel) : CatalogViewEvent
}