package ru.storeva.android.features.dialogs.product_card

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ru.storeva.android.domain.models.ProductModel
import ru.storeva.android.domain.models.ResultModel
import ru.storeva.android.domain.repositories.CartRepository
import ru.storeva.android.domain.usecase.cart.AddToCartUseCase
import ru.storeva.android.domain.usecase.cart.RemoveFromCartUseCase
import ru.storeva.android.domain.usecase.catalog.GetProductCardUseCase
import ru.storeva.android.features.SnackBarManager

class DefaultProductCardComponent(
    componentContent: ComponentContext,
    private val productId: Long,
    snackBarManager: SnackBarManager,
    private val getProductCardUseCase: GetProductCardUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val cartRepository: CartRepository,
) : ProductCardComponent(
        componentContent = componentContent,
        initialState = ProductCardViewState(
            isLoading = true,
            product = null,
        ),
        reducer = ProductCardReducer(),
        snackBarManager = snackBarManager,
    ) {
    private var job: Job? = null

    override fun onEvent(event: ProductCardViewEvent) {
        when (event) {
            is ProductCardViewEvent.OnError -> {
                reduce(event)
                showError(event.error)
            }

            is ProductCardViewEvent.OnProductLoaded -> {
                reduce(event)
            }

            is ProductCardViewEvent.OnThrowError -> {
                reduce(event)
                showThrowError(event.throwable)
            }

            is ProductCardViewEvent.OnCartLoaded -> {
                reduce(event)
            }

            ProductCardViewEvent.OnAddToCart -> {
                state.value.product?.let { addToCart(it) }
            }

            ProductCardViewEvent.OnRemoveFromCart -> {
                state.value.product?.let { removeFromCart(it) }
            }
        }
    }

    override fun onResume() {
        loadProduct()
    }

    private fun loadProduct() {
        coroutineScope.launch {
            getProductCardUseCase
                .invoke(productId)
                .catch {
                    onEvent(ProductCardViewEvent.OnThrowError(it))
                }.collect { resultModel ->
                    when (resultModel) {
                        is ResultModel.Error -> {
                            onEvent(ProductCardViewEvent.OnError(resultModel.message ?: "Что-то пошло не так"))
                        }

                        ResultModel.Loading -> {}

                        is ResultModel.Success<ProductModel> -> {
                            onEvent(ProductCardViewEvent.OnProductLoaded(resultModel.data))
                            subscribeToCart()
                        }
                    }
                }
        }
    }

    private fun addToCart(product: ProductModel) {
        val params = AddToCartUseCase.Params(
            product = product.copy(count = product.count + 1),
        )
        job?.cancel()
        job = coroutineScope.launch {
            addToCartUseCase
                .invoke(params)
                .catch {
                    onEvent(ProductCardViewEvent.OnThrowError(it))
                }.collect { resultModel ->
                    if (resultModel is ResultModel.Error) {
                        onEvent(ProductCardViewEvent.OnError(resultModel.message ?: "Что-то пошло не так"))
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
            removeFromCartUseCase
                .invoke(params)
                .catch {
                    onEvent(ProductCardViewEvent.OnThrowError(it))
                }.collect { resultModel ->
                    if (resultModel is ResultModel.Error) {
                        onEvent(ProductCardViewEvent.OnError(resultModel.message ?: "Что-то пошло не так"))
                    }
                }
        }
    }

    private fun subscribeToCart() {
        coroutineScope.launch {
            cartRepository.cartSubject.collect {
                onEvent(ProductCardViewEvent.OnCartLoaded(it))
            }
        }
    }
}