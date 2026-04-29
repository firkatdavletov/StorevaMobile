package ru.storeva.app.domain.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import ru.storeva.app.domain.models.CategoryModel
import ru.storeva.app.domain.models.ProductModel
import ru.storeva.app.domain.models.ResultModel

interface CatalogRepository {
    val catalogSubject: SharedFlow<List<CategoryModel>>

    fun getCategories(): Flow<List<CategoryModel>>

    fun getCategoryById(id: Long): Flow<CategoryModel?>

    fun getProductCard(id: Long): Flow<ResultModel<ProductModel>>

    fun getProducts(categoryId: Long): Flow<ResultModel<List<ProductModel>>>

    fun loadCatalog(): Flow<ResultModel<Boolean>>
}