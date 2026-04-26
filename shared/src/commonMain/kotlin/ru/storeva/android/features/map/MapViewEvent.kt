package ru.storeva.android.features.map

import ru.storeva.android.domain.models.CartModel
import ru.storeva.android.domain.models.DeliveryType
import ru.storeva.android.domain.models.DepartmentModel
import ru.storeva.android.domain.models.GeoAddressModel
import ru.storeva.android.features.base.Reducer

sealed interface MapViewEvent : Reducer.ViewEvent {
    data object OnConfirm : MapViewEvent

    data object OnBackClicked : MapViewEvent

    data object OnSearchAddressClicked : MapViewEvent

    data object OnLoading : MapViewEvent

    data class OnCartLoaded(val cartModel: CartModel) : MapViewEvent

    data class OnShowDepartments(val departments: List<DepartmentModel>) : MapViewEvent

    data class OnMoveToLocation(val latitude: Double, val longitude: Double) : MapViewEvent

    data class OnMapMoved(
        val latitude: Double,
        val longitude: Double,
        val reason: Int,
        val finished: Boolean,
    ) : MapViewEvent

    data class OnChangeDeliveryType(val type: DeliveryType) : MapViewEvent

    data class OnDepartmentSelected(val id: Long) : MapViewEvent

    data class OnFoundAddress(val address: GeoAddressModel) : MapViewEvent

    data class OnThrowError(val throwable: Throwable) : MapViewEvent

    data class OnError(val message: String?) : MapViewEvent

    data object OnDefaultError : MapViewEvent

    data class OnFindAddressError(val message: String) : MapViewEvent
}