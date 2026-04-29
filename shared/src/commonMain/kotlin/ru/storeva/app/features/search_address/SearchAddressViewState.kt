package ru.storeva.app.features.search_address

import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.domain.models.DepartmentModel
import ru.storeva.app.domain.models.GeoAddressModel
import ru.storeva.app.features.base.Reducer

data class SearchAddressViewState(
    val deliveryType: DeliveryType,
    val query: String,
    val isLoading: Boolean,
    val isSearching: Boolean,
    val departments: List<DepartmentModel>,
    val addresses: List<GeoAddressModel>,
) : Reducer.ViewState