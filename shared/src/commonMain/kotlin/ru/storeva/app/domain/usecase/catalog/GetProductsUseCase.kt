package ru.storeva.app.domain.usecase.catalog

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.ProductModel
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.CatalogRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class GetProductsUseCase(
    private val catalogRepository: CatalogRepository,
) : IOUseCase<Long, ResultModel<List<ProductModel>>>() {
    override fun execute(param: Long): Flow<ResultModel<List<ProductModel>>> {
        return catalogRepository.getProducts(param)
    }
}