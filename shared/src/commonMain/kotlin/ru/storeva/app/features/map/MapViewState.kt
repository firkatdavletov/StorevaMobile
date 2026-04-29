package ru.storeva.app.features.map

import ru.storeva.app.domain.models.CityModel
import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.domain.models.DepartmentModel
import ru.storeva.app.features.base.Reducer

data class MapViewState(
    val isLoading: Boolean,
    val isSearching: Boolean,
    val isError: Boolean,
    val deliveryType: DeliveryType,
    val deliveryAddress: String?,
    val workTimeString: String?,
    val city: CityModel?,
    val deliveryPrice: Long?,
    val freeDeliveryPrice: Long?,
    val departments: List<DepartmentModel>,
    val selectedDepartment: Long?,
    val cartDepartment: DepartmentModel?,
    val currentPosition: UiPoint?,
    val confirmEnabled: Boolean,
    val showLocation: Boolean,
    val showBackButton: Boolean,
    val showSearchButton: Boolean,
    val errorMessage: String?,
) : Reducer.ViewState