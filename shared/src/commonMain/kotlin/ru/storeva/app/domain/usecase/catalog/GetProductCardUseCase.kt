package ru.storeva.app.domain.usecase.catalog

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.ProductModel
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.CatalogRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class GetProductCardUseCase(
    private val catalogRepository: CatalogRepository,
) : IOUseCase<Long, ResultModel<ProductModel>>() {
    override fun execute(param: Long): Flow<ResultModel<ProductModel>> {
        return catalogRepository.getProductCard(param)
    }
}