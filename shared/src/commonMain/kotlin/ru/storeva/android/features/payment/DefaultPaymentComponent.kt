package ru.storeva.android.features.payment

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.storeva.android.domain.models.AddressModel
import ru.storeva.android.domain.models.CartItemModel
import ru.storeva.android.domain.models.DeliveryInfoModel
import ru.storeva.android.domain.models.DeliveryType
import ru.storeva.android.domain.models.OrderItemModel
import ru.storeva.android.domain.models.OrderModel
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.repositories.CartRepository
import ru.storeva.android.domain.usecase.cart.ClearCartUseCase
import ru.storeva.android.domain.usecase.cart.UpdateDeliveryAddressUseCase
import ru.storeva.android.domain.usecase.order.CreateOrderUseCase
import ru.storeva.android.domain.usecase.payment.GetPaymentTypesUseCase
import ru.storeva.android.features.SnackBarManager

class DefaultPaymentComponent(
    componentContext: ComponentContext,
    snackBarManager: SnackBarManager,
    private val callbacks: PaymentCallbacks,
    private val cartRepository: CartRepository,
    private val getPaymentTypesUseCase: GetPaymentTypesUseCase,
    private val createOrderUseCase: CreateOrderUseCase,
    private val updateCartAddressUseCase: UpdateDeliveryAddressUseCase,
    private val clearCartUseCase: ClearCartUseCase,
) : PaymentComponent(
        componentContext = componentContext,
        initialState = PaymentViewState(
            isLoading = true,
            deliveryType = DeliveryType.DELIVERY,
            addressString = null,
            departmentName = null,
            isPrivateHome = false,
            entrance = "",
            flat = "",
            comment = "",
            productPrice = 0,
            deliveryPrice = 0,
            totalAmount = 0,
            paymentTypes = emptyList(),
            storeIsClosed = false,
        ),
        snackBarManager = snackBarManager,
    ) {
    private var cartItems: List<CartItemModel> = emptyList()
    private var deliveryAddress: AddressModel? = null
    private var departmentId: Long? = null
    private var deliveryInfoModel: DeliveryInfoModel? = null

    override fun onStart() {
        super.onStart()
        subscribeToCart()
    }

    override fun onEvent(event: PaymentViewEvent) {
        when (event) {
            is PaymentViewEvent.OnCartChanged -> {
                reduce(event)
            }

            is PaymentViewEvent.OnPaymentTypesLoaded -> {
                reduce(event)
            }

            is PaymentViewEvent.OnChangeDeliveryType -> {
                reduce(event)
                updateDeliveryAddress(event.deliveryType)
            }

            PaymentViewEvent.OnBackButtonClicked -> {
                callbacks.navigateBack()
            }

            PaymentViewEvent.OnConfirmButtonClicked -> {
                createOrder()
            }

            PaymentViewEvent.OnChangeAddress -> {
                callbacks.navigateToMap()
            }

            is PaymentViewEvent.OnIsPrivateHouseChanged -> {
                reduce(event)
            }

            is PaymentViewEvent.OnEntranceInputError -> {
                reduce(event)
            }

            is PaymentViewEvent.OnFlatInputError -> {
                reduce(event)
            }

            is PaymentViewEvent.OnEntranceChanged -> {
                reduce(event)
            }

            is PaymentViewEvent.OnFlatChanged -> {
                reduce(event)
            }

            is PaymentViewEvent.OnCommentChanged -> {
                reduce(event)
            }

            is PaymentViewEvent.OnError -> {
                reduce(event)
                showError(event.message)
            }

            is PaymentViewEvent.OnThrowError -> {
                reduce(event)
                showThrowError(event.throwable)
            }
        }
    }

    private fun subscribeToCart() {
        coroutineScope.launch {
            cartRepository.cartSubject.collect { cartModel ->
                withContext(Dispatchers.Main) {
                    onEvent(PaymentViewEvent.OnCartChanged(cartModel))
                }
                cartItems = cartModel.items
                deliveryAddress = cartModel.deliveryAddress
                departmentId = cartModel.department.id
                deliveryInfoModel = cartModel.deliveryInfo
                loadPaymentTypes()
            }
        }
    }

    private suspend fun loadPaymentTypes() {
        getPaymentTypesUseCase
            .invoke(Unit)
            .catch {
                onEvent(PaymentViewEvent.OnThrowError(it))
            }.collect {
                onEvent(PaymentViewEvent.OnPaymentTypesLoaded(it))
            }
    }

    private fun validateInput(): Boolean {
        return when (state.value.deliveryType) {
            DeliveryType.PICKUP -> {
                true
            }

            DeliveryType.DELIVERY -> {
                if (state.value.addressString == null) {
                    return false
                } else {
                    if (state.value.isPrivateHome) {
                        true
                    } else {
                        val entrance = validateEntrance()
                        val flat = validateFlat()
                        return entrance && flat
                    }
                }
            }
        }
    }

    private fun validateEntrance(): Boolean {
        return if (state.value.entrance.isBlank()) {
            onEvent(PaymentViewEvent.OnEntranceInputError("Введите подъезд"))
            onEvent(PaymentViewEvent.OnError("Введите номер подъезда"))
            false
        } else {
            true
        }
    }

    private fun validateFlat(): Boolean {
        return if (state.value.flat.isBlank()) {
            onEvent(PaymentViewEvent.OnFlatInputError("Введите номер квартиры"))
            onEvent(PaymentViewEvent.OnError("Введите номер квартиры"))
            false
        } else {
            true
        }
    }

    private fun createOrder() {
        if (!validateInput()) return

        val departmentId = departmentId ?: return
        coroutineScope.launch {
            val params = CreateOrderUseCase.Params(
                deliveryType = state.value.deliveryType,
                amount = state.value.totalAmount,
                deliveryPrice = state.value.deliveryPrice,
                products = cartItems.map { cartItemModel ->
                    OrderItemModel(
                        productId = cartItemModel.productId,
                        name = cartItemModel.title,
                        quantity = cartItemModel.quantity,
                        price = cartItemModel.price,
                        imageUrl = null,
                        totalPrice = cartItemModel.price * cartItemModel.quantity,
                        unit = cartItemModel.unit,
                    )
                },
                deliveryAddress = deliveryAddress?.copy(
                    entrance = state.value.entrance.toIntOrNull(),
                    flat = state.value.flat,
                ),
                comment = state.value.comment,
                departmentId = departmentId,
            )
            createOrderUseCase
                .invoke(params)
                .catch {
                    onEvent(PaymentViewEvent.OnThrowError(it))
                }.collect { result ->
                    when (result) {
                        is ResultModel.Error -> {
                            onEvent(PaymentViewEvent.OnError(result.message))
                        }

                        ResultModel.Loading -> {
                        }

                        is ResultModel.Success<OrderModel> -> {
                            val order = result.data

                            clearCartUseCase
                                .invoke(Unit)
                                .catch {
                                    onEvent(PaymentViewEvent.OnThrowError(it))
                                }.collect { result ->
                                    when (result) {
                                        is ResultModel.Error -> {
                                            onEvent(PaymentViewEvent.OnError(result.message))
                                        }

                                        ResultModel.Loading -> {}

                                        is ResultModel.Success<Boolean> -> {
                                            updateDeliveryAddress(state.value.deliveryType)
                                            withContext(Dispatchers.Main) {
                                                callbacks.navigateToOrder(order.id)
                                            }
                                        }
                                    }
                                }
                        }
                    }
                }
        }
    }

    private fun updateDeliveryAddress(deliveryType: DeliveryType) {
        val params = when (state.value.deliveryType) {
            DeliveryType.PICKUP -> {
                val departmentId = departmentId ?: return
                UpdateDeliveryAddressUseCase.Params(
                    deliveryType = deliveryType,
                    deliveryAddress = null,
                    departmentId = departmentId,
                    comment = state.value.comment,
                    deliveryInfo = deliveryInfoModel!!,
                )
            }

            DeliveryType.DELIVERY -> {
                val deliveryAddress = deliveryAddress ?: return
                val departmentId = departmentId ?: return
                UpdateDeliveryAddressUseCase.Params(
                    deliveryType = deliveryType,
                    deliveryAddress = deliveryAddress.copy(
                        entrance = state.value.entrance.toIntOrNull(),
                        flat = state.value.flat,
                    ),
                    departmentId = departmentId,
                    comment = state.value.comment,
                    deliveryInfo = deliveryInfoModel!!,
                )
            }
        }

        coroutineScope.launch {
            updateCartAddressUseCase
                .invoke(params)
                .catch {
                    onEvent(PaymentViewEvent.OnThrowError(it))
                }.collect { resultModel ->
                    when (resultModel) {
                        is ResultModel.Error -> {
                            onEvent(PaymentViewEvent.OnError(resultModel.message))
                        }

                        ResultModel.Loading -> {}

                        is ResultModel.Success<Boolean> -> {
                            if (!resultModel.data) {
                                onEvent(PaymentViewEvent.OnError(null))
                            }
                        }
                    }
                }
        }
    }
}