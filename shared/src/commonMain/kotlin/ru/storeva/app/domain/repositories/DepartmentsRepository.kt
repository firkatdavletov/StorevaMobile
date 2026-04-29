package ru.storeva.app.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.DepartmentModel

interface DepartmentsRepository {
    fun getDepartments(): Flow<List<DepartmentModel>>
}