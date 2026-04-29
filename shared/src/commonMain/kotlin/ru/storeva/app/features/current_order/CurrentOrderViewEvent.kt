package ru.storeva.app.features.current_order

import ru.storeva.app.domain.models.OrderModel
import ru.storeva.app.features.base.Reducer

sealed interface CurrentOrderViewEvent : Reducer.ViewEvent {
    data object OnBackClicked : CurrentOrderViewEvent

    data class OnOrderLoaded(val order: OrderModel) : CurrentOrderViewEvent
}