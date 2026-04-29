package ru.storeva.app.data.api.catalog

import ru.storeva.app.data.api.catalog.model.GetCatalogResponseBody
import ru.storeva.app.data.api.catalog.model.GetProductResponseBody
import ru.storeva.app.data.api.catalog.model.GetProductsResponseBody

interface CatalogApi {
    suspend fun getCatalog(): GetCatalogResponseBody

    suspend fun getProduct(productId: Long): GetProductResponseBody

    suspend fun getProductsByCategory(categoryId: Long): GetProductsResponseBody
}