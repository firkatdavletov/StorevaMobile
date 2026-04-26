package ru.storeva.android.domain.usecase.geo

import kotlinx.coroutines.flow.Flow
import ru.storeva.android.domain.models.GeoAddressModel
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.repositories.GeoRepository
import ru.storeva.android.domain.usecase.base.IOUseCase

class SearchAddressUseCase(
    private val geoRepository: GeoRepository,
) : IOUseCase<String, ResultModel<List<GeoAddressModel>>>() {
    override fun execute(param: String): Flow<ResultModel<List<GeoAddressModel>>> {
        return geoRepository.searchAddress(query = param)
    }
}