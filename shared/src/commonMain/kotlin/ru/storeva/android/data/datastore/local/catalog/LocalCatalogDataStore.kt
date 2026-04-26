package ru.storeva.android.data.datastore.local.catalog

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.data.entities.CategoryEntity
import ru.storeva.android.data.entities.ProductEntity

interface LocalCatalogDataStore {
    fun getCategories(): Flow<List<CategoryEntity>>

    fun saveCategories(categories: List<CategoryEntity>)

    fun getProducts(categoryId: Long): Flow<List<ProductEntity>?>

    fun getProduct(productId: Long): Flow<ProductEntity?>

    fun getCategory(id: Long): Flow<CategoryEntity?>
}