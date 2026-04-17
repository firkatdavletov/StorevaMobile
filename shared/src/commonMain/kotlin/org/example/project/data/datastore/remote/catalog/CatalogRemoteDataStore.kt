package org.example.project.data.datastore.remote.catalog

import org.example.project.data.api.catalog.model.GetCatalogResponseBody
import org.example.project.data.api.catalog.model.GetProductResponseBody
import org.example.project.data.api.catalog.model.GetProductsResponseBody

interface CatalogRemoteDataStore {
    suspend fun getCatalog(): GetCatalogResponseBody

    suspend fun getProduct(id: Long): GetProductResponseBody

    suspend fun getProducts(categoryId: Long): GetProductsResponseBody
}