package ru.storeva.app.features.cart

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.data.datastore.local.SecurityStorage
import ru.storeva.app.domain.models.CartItemModel
import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.domain.models.ProductModel
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.CartRepository
import ru.storeva.app.domain.usecase.cart.AddToCartUseCase
import ru.storeva.app.domain.usecase.cart.LoadCartUseCase
import ru.storeva.app.domain.usecase.cart.RemoveFromCartUseCase
import ru.storeva.app.domain.usecase.catalog.GetProductCardUseCase

class DefaultCartComponent(
    componentContext: ComponentContext,
    snackBarManager: SnackBarManager,
    private val callbacks: CartViewCallbacks,
    private val loadCartUseCase: LoadCartUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val getProductCardUseCase: GetProductCardUseCase,
    private val cartRepository: CartRepository,
    private val securityStorage: SecurityStorage,
) : CartComponent(
        componentContext = componentContext,
        initialState = CartViewState(
            totalPrice = 0,
            deliveryPrice = 0,
            productsPrice = 0,
            freeDeliveryPrice = null,
            cartItems = emptyList(),
            deliveryType = DeliveryType.PICKUP,
            addressString = "",
            continueText = "ВЫБЕРИТЕ АДРЕС",
        ),
        snackBarManager = snackBarManager,
        reducer = CartReducer(),
    ) {
    private var job: Job? = null

    init {
        subscribeToCart()
    }

    override fun onResume() {
        getCart()
    }

    override fun onEvent(event: CartViewEvent) {
        when (event) {
            CartViewEvent.OnBackClick -> {
                callbacks.onBackClicked()
            }

            CartViewEvent.OnConfirmButtonClicked -> {
                if (securityStorage.getAccessToken().isBlank()) {
                    callbacks.navigateToLogin()
                } else {
                    callbacks.navigateToPayment()
                }
            }

            is CartViewEvent.OnCartLoaded -> {
                reduce(event)
            }

            is CartViewEvent.OnAddToCart -> {
                addToCart(event.product)
            }

            is CartViewEvent.OnRemoveFromCart -> {
                removeFromCart(event.product)
            }

            is CartViewEvent.OnError -> {
                reduce(event)
                showError(event.message)
            }

            is CartViewEvent.OnThrowError -> {
                reduce(event)
                showThrowError(event.throwable)
            }
        }
    }

    fun getCart() {
        coroutineScope.launch {
            loadCartUseCase
                .invoke(Unit)
                .catch {
                    onEvent(CartViewEvent.OnThrowError(it))
                }.collect {}
        }
    }

    private fun subscribeToCart() {
        coroutineScope.launch {
            cartRepository.cartSubject.collect {
                onEvent(CartViewEvent.OnCartLoaded(it))
            }
        }
    }

    private suspend fun getProduct(
        productId: Long,
        onSuccess: (ProductModel) -> Unit,
    ) {
        getProductCardUseCase
            .invoke(productId)
            .catch {
                onEvent(CartViewEvent.OnThrowError(it))
            }.collect { resultModel ->
                when (resultModel) {
                    is ResultModel.Error -> {
                        onEvent(CartViewEvent.OnError(resultModel.message))
                    }

                    ResultModel.Loading -> {}

                    is ResultModel.Success<ProductModel> -> {
                        onSuccess(resultModel.data)
                    }
                }
            }
    }

    private fun addToCart(cartItemModel: CartItemModel) {
        println("on add to cart")
        coroutineScope.launch {
            getProduct(cartItemModel.productId) {
                addToCart(it.copy(count = cartItemModel.quantity))
            }
        }
    }

    private fun removeFromCart(cartItemModel: CartItemModel) {
        coroutineScope.launch {
            getProduct(cartItemModel.productId) {
                removeFromCart(it.copy(count = cartItemModel.quantity))
            }
        }
    }

    private fun addToCart(product: ProductModel) {
        val params = AddToCartUseCase.Params(
            product = product.copy(count = product.count + 1),
        )
        job?.cancel()
        job = coroutineScope.launch {
            println(" on add")
            addToCartUseCase
                .invoke(params)
                .catch {
                    onEvent(CartViewEvent.OnThrowError(it))
                }.collect {
                    if (it is ResultModel.Error) {
                        onEvent(CartViewEvent.OnError(it.message))
                    }
                }
        }
    }

    private fun removeFromCart(product: ProductModel) {
        val params = RemoveFromCartUseCase.Params(
            product = product.copy(count = product.count - 1),
        )
        job?.cancel()
        job = coroutineScope.launch {
            println(" on remove")
            removeFromCartUseCase
                .invoke(params)
                .catch {
                    onEvent(CartViewEvent.OnThrowError(it))
                }.collect {
                    if (it is ResultModel.Error) {
                        onEvent(CartViewEvent.OnError(it.message))
                    }
                }
        }
    }
}