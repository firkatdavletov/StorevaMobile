package org.example.project.data.datastore.remote.catalog

import org.example.project.data.api.catalog.CatalogApi
import org.example.project.data.api.catalog.model.GetCatalogResponseBody
import org.example.project.data.api.catalog.model.GetProductResponseBody
import org.example.project.data.api.catalog.model.GetProductsResponseBody

class DefaultCatalogRemoteDataStore(
    private val catalogApi: CatalogApi,
) : CatalogRemoteDataStore {
    override suspend fun getCatalog(): GetCatalogResponseBody {
        return catalogApi.getCatalog()
    }

    override suspend fun getProduct(id: Long): GetProductResponseBody {
        return catalogApi.getProduct(id)
    }

    override suspend fun getProducts(categoryId: Long): GetProductsResponseBody {
        return catalogApi.getProductsByCategory(categoryId)
    }
}