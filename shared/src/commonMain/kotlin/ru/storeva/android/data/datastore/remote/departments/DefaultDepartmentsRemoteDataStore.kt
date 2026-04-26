package ru.storeva.android.data.datastore.remote.departments

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.storeva.android.data.api.departments_api.DepartmentApi
import ru.storeva.android.data.entities.DepartmentEntity

class DefaultDepartmentsRemoteDataStore(
    private val api: DepartmentApi,
) : DepartmentsRemoteDataStore {
    override fun loadDepartments(): Flow<List<DepartmentEntity>> {
        return flow {
            emit(api.getDepartments())
        }.map { it.departments }
    }
}