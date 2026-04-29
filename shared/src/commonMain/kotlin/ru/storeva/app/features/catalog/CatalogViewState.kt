package ru.storeva.app.features.catalog

import ru.storeva.app.domain.models.ProductModel
import ru.storeva.app.features.base.Reducer

data class CatalogViewState(
    val title: String,
    val products: List<ProductModel>,
    val amount: Long,
    val productsPrice: Long,
    val freeDeliveryPrice: Long?,
) : Reducer.ViewState