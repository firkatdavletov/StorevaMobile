package ru.storeva.app.features.search_address

import ru.storeva.app.domain.models.CartModel
import ru.storeva.app.domain.models.DepartmentModel
import ru.storeva.app.domain.models.GeoAddressModel
import ru.storeva.app.features.base.Reducer

sealed interface SearchAddressViewEvent : Reducer.ViewEvent {
    data object OnBackClicked : SearchAddressViewEvent

    data object OnMapClicked : SearchAddressViewEvent

    data class OnQueryChanged(val query: String) : SearchAddressViewEvent

    data class OnCartLoaded(val cart: CartModel) : SearchAddressViewEvent

    data class OnDepartmentsLoaded(val departments: List<DepartmentModel>) : SearchAddressViewEvent

    data class OnSearchComplete(val addresses: List<GeoAddressModel>) : SearchAddressViewEvent

    data class OnAddressClicked(val address: GeoAddressModel) : SearchAddressViewEvent

    data class OnError(val message: String?) : SearchAddressViewEvent

    data class OnThrowError(val throwable: Throwable) : SearchAddressViewEvent
}