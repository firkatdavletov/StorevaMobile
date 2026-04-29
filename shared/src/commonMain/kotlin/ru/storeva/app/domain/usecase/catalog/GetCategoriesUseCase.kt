package ru.storeva.app.domain.usecase.catalog

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.CategoryModel
import ru.storeva.app.domain.repositories.CatalogRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class GetCategoriesUseCase(
    private val catalogRepository: CatalogRepository,
) : IOUseCase<Unit, List<CategoryModel>>() {
    override fun execute(param: Unit): Flow<List<CategoryModel>> {
        return catalogRepository.getCategories()
    }
}