package ru.storeva.android.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.GeoAddressModel
import ru.storeva.android.domain.models.ResultModel

interface GeoRepository {
    fun getAddress(
        query: String?,
        uri: String?,
        entrance: Int?,
    ): Flow<ResultModel<GeoAddressModel>>

    fun searchAddress(query: String): Flow<ResultModel<List<GeoAddressModel>>>
}