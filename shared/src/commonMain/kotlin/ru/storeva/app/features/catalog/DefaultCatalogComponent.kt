package ru.storeva.app.features.catalog

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.storeva.app.domain.models.ProductModel
import ru.storeva.app.domain.models.ResultModel
import ru.storeva.app.domain.repositories.CartRepository
import ru.storeva.app.domain.usecase.cart.AddToCartUseCase
import ru.storeva.app.domain.usecase.cart.RemoveFromCartUseCase
import ru.storeva.app.domain.usecase.catalog.GetProductsUseCase

class DefaultCatalogComponent(
    componentContext: ComponentContext,
    title: String,
    private val categoryId: Long,
    private val callbacks: CatalogCallbacks,
    private val getProductsUseCase: GetProductsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val cartRepository: CartRepository,
) : CatalogComponent(
        componentContext = componentContext,
        initialState = CatalogViewState(
            title = title,
            products = emptyList(),
            amount = 0,
            freeDeliveryPrice = null,
            productsPrice = 0,
        ),
        reducer = CatalogReducer(),
    ) {
    private var job: Job? = null

    override fun onResume() {
        coroutineScope.launch {
            getProductsUseCase
                .invoke(categoryId)
                .catch {
                }.collect { resultModel ->
                    when (resultModel) {
                        is ResultModel.Error -> {
                        }

                        ResultModel.Loading -> {}

                        is ResultModel.Success<List<ProductModel>> -> {
                            withContext(Dispatchers.Main) {
                                onEvent(CatalogViewEvent.OnProductsLoaded(resultModel.data))
                                subscribeToCart()
                            }
                        }
                    }
                }
        }
    }

    override fun onEvent(event: CatalogViewEvent) {
        when (event) {
            CatalogViewEvent.OnAddressClicked -> {
                TODO()
            }

            CatalogViewEvent.OnBackClicked -> {
                callbacks.onBack()
            }

            is CatalogViewEvent.OnProductsLoaded -> {
                reduce(event)
            }

            is CatalogViewEvent.OnCategoryClicked -> {
                TODO()
            }

            is CatalogViewEvent.OnUserLoaded -> {
                TODO()
            }

            is CatalogViewEvent.OnCategoryLoaded -> {
                TODO()
            }

            is CatalogViewEvent.OnAddToCart -> {
                addToCart(event.product)
            }

            is CatalogViewEvent.OnRemoveFromCart -> {
                removeFromCart(event.product)
            }

            is CatalogViewEvent.OnCartLoaded -> {
                reduce(event)
            }

            CatalogViewEvent.OnCartButtonClicked -> {
                callbacks.onNavigateToCart()
            }

            is CatalogViewEvent.OnProductCardClicked -> {
                callbacks.showProductCard(event.product.id)
            }
        }
    }

    private fun addToCart(product: ProductModel) {
        val params = AddToCartUseCase.Params(
            product = product.copy(count = product.count + product.countStep),
        )
        job?.cancel()
        job = coroutineScope.launch {
            addToCartUseCase
                .invoke(params)
                .catch {
                    print(it.message)
                }.collect {
                    print(it)
                }
        }
    }

    private fun removeFromCart(product: ProductModel) {
        val params = RemoveFromCartUseCase.Params(
            product = product.copy(count = product.count - product.countStep),
        )
        job?.cancel()
        job = coroutineScope.launch {
            removeFromCartUseCase
                .invoke(params)
                .catch {
                    print(it.message)
                }.collect {
                    print(it)
                }
        }
    }

    private fun subscribeToCart() {
        coroutineScope.launch {
            cartRepository.cartSubject.collect {
                onEvent(CatalogViewEvent.OnCartLoaded(it))
            }
        }
    }
}