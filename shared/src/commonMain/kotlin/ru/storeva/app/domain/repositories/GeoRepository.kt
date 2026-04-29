package ru.storeva.app.domain.repositories

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.GeoAddressModel
import ru.storeva.app.domain.models.ResultModel

interface GeoRepository {
    fun getAddress(
        query: String?,
        uri: String?,
        entrance: Int?,
    ): Flow<ResultModel<GeoAddressModel>>

    fun searchAddress(query: String): Flow<ResultModel<List<GeoAddressModel>>>
}