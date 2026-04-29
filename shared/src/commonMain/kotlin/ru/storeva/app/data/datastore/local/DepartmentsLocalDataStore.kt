package ru.storeva.app.data.datastore.local

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.data.entities.DepartmentEntity

interface DepartmentsLocalDataStore {
    suspend fun findDepartmentsOnce(): List<DepartmentEntity>

    fun findDepartments(): Flow<List<DepartmentEntity>>

    fun saveDepartments(departments: List<DepartmentEntity>)
}