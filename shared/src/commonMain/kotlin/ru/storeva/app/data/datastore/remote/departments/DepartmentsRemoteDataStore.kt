package ru.storeva.app.data.datastore.remote.departments

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.data.entities.DepartmentEntity

interface DepartmentsRemoteDataStore {
    fun loadDepartments(): Flow<List<DepartmentEntity>>
}