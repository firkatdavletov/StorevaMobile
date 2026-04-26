package ru.storeva.android.data.api.catalog

import ru.storeva.android.data.api.catalog.model.GetCatalogResponseBody
import ru.storeva.android.data.api.catalog.model.GetProductResponseBody
import ru.storeva.android.data.api.catalog.model.GetProductsResponseBody

interface CatalogApi {
    suspend fun getCatalog(): GetCatalogResponseBody

    suspend fun getProduct(productId: Long): GetProductResponseBody

    suspend fun getProductsByCategory(categoryId: Long): GetProductsResponseBody
}