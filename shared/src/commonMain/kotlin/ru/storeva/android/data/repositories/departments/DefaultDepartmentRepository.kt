package ru.storeva.android.data.repositories.departments

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.storeva.android.data.datastore.local.DepartmentsLocalDataStore
import ru.storeva.android.data.datastore.remote.departments.DepartmentsRemoteDataStore
import ru.storeva.android.data.mapper.DepartmentMapper
import ru.storeva.android.domain.models.DepartmentModel
import ru.storeva.android.domain.repositories.DepartmentsRepository

class DefaultDepartmentRepository(
    private val remoteStore: DepartmentsRemoteDataStore,
    private val localStore: DepartmentsLocalDataStore,
    private val departmentMapper: DepartmentMapper,
) : DepartmentsRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getDepartments(): Flow<List<DepartmentModel>> {
        return remoteStore.loadDepartments().map {
            departmentMapper.toModel(it)
        }
    }
}