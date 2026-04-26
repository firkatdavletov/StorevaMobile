package ru.storeva.android.data.datastore.remote.departments

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.data.entities.DepartmentEntity

interface DepartmentsRemoteDataStore {
    fun loadDepartments(): Flow<List<DepartmentEntity>>
}