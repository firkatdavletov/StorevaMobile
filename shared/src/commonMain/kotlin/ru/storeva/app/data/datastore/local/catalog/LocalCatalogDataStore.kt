package ru.storeva.app.data.datastore.local.catalog

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.data.entities.CategoryEntity
import ru.storeva.app.data.entities.ProductEntity

interface LocalCatalogDataStore {
    fun getCategories(): Flow<List<CategoryEntity>>

    fun saveCategories(categories: List<CategoryEntity>)

    fun getProducts(categoryId: Long): Flow<List<ProductEntity>?>

    fun getProduct(productId: Long): Flow<ProductEntity?>

    fun getCategory(id: Long): Flow<CategoryEntity?>
}