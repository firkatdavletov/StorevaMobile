package ru.storeva.android.features.search_address

import ru.storeva.android.domain.models.DeliveryType
import ru.storeva.android.domain.models.DepartmentModel
import ru.storeva.android.domain.models.GeoAddressModel
import ru.storeva.android.features.base.Reducer

data class SearchAddressViewState(
    val deliveryType: DeliveryType,
    val query: String,
    val isLoading: Boolean,
    val isSearching: Boolean,
    val departments: List<DepartmentModel>,
    val addresses: List<GeoAddressModel>,
) : Reducer.ViewState