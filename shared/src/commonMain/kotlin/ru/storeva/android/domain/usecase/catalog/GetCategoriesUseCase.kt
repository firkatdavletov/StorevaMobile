package ru.storeva.android.domain.usecase.catalog

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.CategoryModel
import ru.storeva.android.domain.repositories.CatalogRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class GetCategoriesUseCase(
    private val catalogRepository: CatalogRepository,
) : IOUseCase<Unit, List<CategoryModel>>() {
    override fun execute(param: Unit): Flow<List<CategoryModel>> {
        return catalogRepository.getCategories()
    }
}