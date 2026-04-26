package ru.storeva.android.domain.usecase.catalog

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.ProductModel
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.repositories.CatalogRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class GetProductCardUseCase(
    private val catalogRepository: CatalogRepository,
) : IOUseCase<Long, ResultModel<ProductModel>>() {
    override fun execute(param: Long): Flow<ResultModel<ProductModel>> {
        return catalogRepository.getProductCard(param)
    }
}