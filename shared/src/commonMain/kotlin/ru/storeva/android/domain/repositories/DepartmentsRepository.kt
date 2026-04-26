package ru.storeva.android.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.DepartmentModel

interface DepartmentsRepository {
    fun getDepartments(): Flow<List<DepartmentModel>>
}