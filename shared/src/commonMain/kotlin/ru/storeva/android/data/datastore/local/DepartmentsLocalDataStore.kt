package ru.storeva.android.data.datastore.local

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.data.entities.DepartmentEntity

interface DepartmentsLocalDataStore {
    suspend fun findDepartmentsOnce(): List<DepartmentEntity>

    fun findDepartments(): Flow<List<DepartmentEntity>>

    fun saveDepartments(departments: List<DepartmentEntity>)
}