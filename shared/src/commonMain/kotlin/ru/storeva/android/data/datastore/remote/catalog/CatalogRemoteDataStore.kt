package ru.storeva.android.data.datastore.remote.catalog

import ru.storeva.android.data.api.catalog.model.GetCatalogResponseBody
import ru.storeva.android.data.api.catalog.model.GetProductResponseBody
import ru.storeva.android.data.api.catalog.model.GetProductsResponseBody

interface CatalogRemoteDataStore {
    suspend fun getCatalog(): GetCatalogResponseBody

    suspend fun getProduct(id: Long): GetProductResponseBody

    suspend fun getProducts(categoryId: Long): GetProductsResponseBody
}