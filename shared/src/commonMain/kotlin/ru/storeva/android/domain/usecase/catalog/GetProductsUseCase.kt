package ru.storeva.android.domain.usecase.catalog

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.ProductModel
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.repositories.CatalogRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class GetProductsUseCase(
    private val catalogRepository: CatalogRepository,
) : IOUseCase<Long, ResultModel<List<ProductModel>>>() {
    override fun execute(param: Long): Flow<ResultModel<List<ProductModel>>> {
        return catalogRepository.getProducts(param)
    }
}