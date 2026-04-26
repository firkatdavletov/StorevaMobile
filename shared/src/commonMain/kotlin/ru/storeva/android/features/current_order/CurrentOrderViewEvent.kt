package ru.storeva.android.features.current_order

import ru.storeva.android.domain.models.OrderModel
import ru.storeva.android.features.base.Reducer

sealed interface CurrentOrderViewEvent : Reducer.ViewEvent {
    data object OnBackClicked : CurrentOrderViewEvent

    data class OnOrderLoaded(val order: OrderModel) : CurrentOrderViewEvent
}