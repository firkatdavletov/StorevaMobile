package ru.storeva.app.data.repositories.catalog

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.storeva.app.data.datastore.local.catalog.LocalCatalogDataStore
import ru.storeva.app.data.datastore.remote.catalog.CatalogRemoteDataStore
import ru.storeva.app.data.mapper.CategoryMapper
import ru.storeva.app.data.mapper.ProductMapper
import ru.storeva.app.domain.models.CategoryModel
import ru.storeva.app.domain.models.ProductModel
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.CatalogRepository

class DefaultCatalogRepository(
    private val catalogRemoteDataStore: CatalogRemoteDataStore,
    private val catalogLocalDataStore: LocalCatalogDataStore,
    private val categoryMapper: CategoryMapper,
    private val productMapper: ProductMapper,
) : CatalogRepository {

    private val _catalogSubject = MutableSharedFlow<List<CategoryModel>>(replay = 1)

    override val catalogSubject: SharedFlow<List<CategoryModel>> = _catalogSubject.asSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getCategories(): Flow<List<CategoryModel>> {
        return catalogLocalDataStore.getCategories().flatMapConcat {
            flow {
                emit(categoryMapper.toModel(it))
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getCategoryById(id: Long): Flow<CategoryModel?> {
        return catalogLocalDataStore.getCategory(id).map {
            it?.let { categoryMapper.toModel(it) }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getProducts(categoryId: Long): Flow<ResultModel<List<ProductModel>>> {
        return flow {
            val response = catalogRemoteDataStore.getProducts(categoryId)

            if (response.success) {
                emit(ResultModel.Success(productMapper.toModel(response.products)))
            } else {
                emit(ResultModel.Error(response.error, response.code))
            }
        }
    }

    override fun loadCatalog(): Flow<ResultModel<Boolean>> {
        return flow {
            emit(ResultModel.Loading)
            val response = catalogRemoteDataStore.getCatalog()
            if (response.success) {
                val model = categoryMapper.toModel(response.catalog)
                _catalogSubject.emit(model)
                emit(ResultModel.Success(true))
            } else {
                emit(ResultModel.Error(response.error, response.code))
            }
        }
    }

    override fun getProductCard(id: Long): Flow<ResultModel<ProductModel>> {
        return flow {
            emit(ResultModel.Loading)
            val response = catalogRemoteDataStore.getProduct(id)

            if (response.success && response.product != null) {
                emit(ResultModel.Success(productMapper.toModel(response.product)))
            } else {
                emit(ResultModel.Error(response.error, response.code))
            }
        }
    }
}