package ru.storeva.app.feature.current_order

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.features.current_order.CurrentOrderComponent
import ru.storeva.app.features.current_order.CurrentOrderViewEvent

@Composable
fun CurrentOrderScreen(component: CurrentOrderComponent) {
    val state by component.state.subscribeAsState()

    BackHandler {
        component.onEvent(CurrentOrderViewEvent.OnBackClicked)
    }

    CurrentOrderContent(
        deliveryType = DeliveryType.DELIVERY,
        addressString = "ул. Щербакова 150/2б кв. 88",
        orderNumber = state.number,
        status = state.status,
        orderItems = state.items,
        productsPrice = state.productsPrice,
        deliveryPrice = state.deliveryPrice,
        totalPrice = state.deliveryPrice,
        comment = state.comment,
        onBackButtonClicked = {
            component.onEvent(CurrentOrderViewEvent.OnBackClicked)
        },
    )
}