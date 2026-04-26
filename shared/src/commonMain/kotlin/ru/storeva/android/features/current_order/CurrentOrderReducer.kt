package ru.storeva.android.features.current_order

import ru.storeva.android.domain.models.OrderStatus
import ru.storeva.android.features.base.Reducer

class CurrentOrderReducer : Reducer<CurrentOrderViewState, CurrentOrderViewEvent, CurrentOrderViewEffect> {
    override fun reduce(
        state: CurrentOrderViewState,
        event: CurrentOrderViewEvent,
    ): CurrentOrderViewState {
        return when (event) {
            is CurrentOrderViewEvent.OnOrderLoaded -> {
                state.copy(
                    number = event.order.id.toString(),
                    deliveryType = event.order.deliveryType,
                    addressString = event.order.deliveryAddress ?: "",
                    status = OrderStatus.getTitle(event.order.status),
                    items = event.order.items,
                    deliveryPrice = event.order.deliveryPrice,
                    totalAmount = event.order.totalAmount,
                    productsPrice = event.order.items.sumOf { it.price },
                    comment = event.order.comment.orEmpty(),
                )
            }

            else -> {
                state
            }
        }
    }

    override fun handleEvent(event: CurrentOrderViewEvent): CurrentOrderViewEffect? {
        TODO("Not yet implemented")
    }
}