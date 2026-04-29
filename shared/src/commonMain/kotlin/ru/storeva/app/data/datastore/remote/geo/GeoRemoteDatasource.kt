package ru.storeva.app.data.datastore.remote.geo

import ru.storeva.app.data.api.map_api.GetAddressResponseBody
import ru.storeva.app.data.api.map_api.SearchAddressResponseBody

interface GeoRemoteDatasource {
    suspend fun getAddress(
        query: String?,
        uri: String?,
        entrance: Int?,
    ): GetAddressResponseBody

    suspend fun searchAddress(query: String): SearchAddressResponseBody
}