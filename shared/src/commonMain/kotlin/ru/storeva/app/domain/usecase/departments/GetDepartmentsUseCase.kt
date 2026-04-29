package ru.storeva.app.domain.usecase.departments

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.storeva.app.domain.models.DepartmentModel
import ru.storeva.app.domain.repositories.CartRepository
import ru.storeva.app.domain.repositories.DepartmentsRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class GetDepartmentsUseCase(
    private val repository: DepartmentsRepository,
    private val cartRepository: CartRepository,
) : IOUseCase<Unit, List<DepartmentModel>>() {
    override fun execute(param: Unit): Flow<List<DepartmentModel>> {
        val cartReplayCache = cartRepository.cartSubject.replayCache.firstOrNull()
        val selectedDepartmentId = cartReplayCache?.department?.id
        return repository.getDepartments().map { departments ->
            departments.map { department ->
                department.copy(
                    selected = department.id == selectedDepartmentId,
                )
            }
        }
    }
}