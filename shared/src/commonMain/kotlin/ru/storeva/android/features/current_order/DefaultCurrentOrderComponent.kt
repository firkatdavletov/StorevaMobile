package ru.storeva.android.features.current_order

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ru.storeva.android.domain.models.DeliveryType
import ru.storeva.android.domain.models.OrderModel
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.repositories.OrderRepository
import ru.storeva.android.domain.usecase.order.GetOrderByIdUseCase
import ru.storeva.android.features.home.HomeComponent
import ru.storeva.android.features.payment.PaymentComponent

class DefaultCurrentOrderComponent(
    componentContext: ComponentContext,
    private val fromScreen: String?,
    private val callbacks: CurrentOrderCallbacks,
    private val getOrderByIdUseCase: GetOrderByIdUseCase,
    private val orderId: Long,
    private val orderRepository: OrderRepository,
) : CurrentOrderComponent(
        componentContext = componentContext,
        initialState = CurrentOrderViewState(
            number = "",
            deliveryType = DeliveryType.DELIVERY,
            addressString = "",
            status = "",
            items = emptyList(),
            deliveryPrice = 0,
            totalAmount = 0,
            productsPrice = 0,
            comment = "",
        ),
    ) {

    override fun onStart() {
        super.onStart()
        initData()
    }

    override fun onEvent(event: CurrentOrderViewEvent) {
        when (event) {
            is CurrentOrderViewEvent.OnOrderLoaded -> {
                reduce(event)
            }

            CurrentOrderViewEvent.OnBackClicked -> {
                when (fromScreen) {
                    HomeComponent::class.simpleName -> {
                        callbacks.navigateToBack()
                    }

                    PaymentComponent::class.simpleName -> {
                        callbacks.navigateToHome()
                    }
                }
            }
        }
    }

    private fun initData() {
        coroutineScope.launch {
            orderRepository.ordersSubject.collect {
                val currentOrder = it.firstOrNull { orderModel -> orderModel.id == orderId }
                if (currentOrder != null) {
                    onEvent(CurrentOrderViewEvent.OnOrderLoaded(currentOrder))
                }
            }
        }
        getCurrentOrder()
    }

    private fun getCurrentOrder() {
        coroutineScope.launch {
            getOrderByIdUseCase(orderId)
                .catch {
                }.collect { result ->
                    when (result) {
                        is ResultModel.Error -> {
                        }

                        ResultModel.Loading -> {
                        }

                        is ResultModel.Success<OrderModel> -> {
                            onEvent(CurrentOrderViewEvent.OnOrderLoaded(result.data))
                        }
                    }
                }
        }
    }
}