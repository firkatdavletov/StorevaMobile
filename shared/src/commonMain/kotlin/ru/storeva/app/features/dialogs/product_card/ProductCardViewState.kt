package ru.storeva.app.features.dialogs.product_card

import ru.storeva.app.domain.models.ProductModel
import ru.storeva.app.features.base.Reducer

data class ProductCardViewState(
    val isLoading: Boolean,
    val product: ProductModel?,
) : Reducer.ViewState