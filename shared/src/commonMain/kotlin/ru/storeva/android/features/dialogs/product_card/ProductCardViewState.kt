package ru.storeva.android.features.dialogs.product_card

import ru.storeva.android.domain.models.ProductModel
import ru.storeva.android.features.base.Reducer

data class ProductCardViewState(
    val isLoading: Boolean,
    val product: ProductModel?,
) : Reducer.ViewState