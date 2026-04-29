package ru.storeva.app.domain.usecase.geo

import kotlinx.coroutines.flow.Flow
import ru.storeva.app.domain.models.GeoAddressModel
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.GeoRepository
import ru.storeva.app.domain.usecase.base.IOUseCase

class SearchAddressUseCase(
    private val geoRepository: GeoRepository,
) : IOUseCase<String, ResultModel<List<GeoAddressModel>>>() {
    override fun execute(param: String): Flow<ResultModel<List<GeoAddressModel>>> {
        return geoRepository.searchAddress(query = param)
    }
}