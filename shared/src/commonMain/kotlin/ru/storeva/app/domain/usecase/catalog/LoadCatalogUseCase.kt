package ru.storeva.app.domain.usecase.catalog

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.CatalogRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class LoadCatalogUseCase(
    private val catalogRepository: CatalogRepository,
) : IOUseCase<Unit, ResultModel<Boolean>>() {
    override fun execute(param: Unit): Flow<ResultModel<Boolean>> {
        return catalogRepository.loadCatalog()
    }
}