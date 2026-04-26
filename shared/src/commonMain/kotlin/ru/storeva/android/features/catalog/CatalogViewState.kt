package ru.storeva.android.features.catalog

import ru.storeva.android.domain.models.ProductModel
import ru.storeva.android.features.base.Reducer

data class CatalogViewState(
    val title: String,
    val products: List<ProductModel>,
    val amount: Long,
    val productsPrice: Long,
    val freeDeliveryPrice: Long?,
) : Reducer.ViewState