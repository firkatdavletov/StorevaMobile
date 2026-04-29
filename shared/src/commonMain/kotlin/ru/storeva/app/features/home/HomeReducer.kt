package ru.storeva.app.features.home

import ru.storeva.app.domain.models.DeliveryType
import ru.storeva.app.features.base.Reducer
import ru.storeva.app.features.mapper.OrderUIModelMapper

class HomeReducer(
    private val orderUIModelMapper: OrderUIModelMapper,
) : Reducer<HomeViewState, HomeViewEvent, HomeViewEffect> {
    override fun reduce(
        state: HomeViewState,
        event: HomeViewEvent,
    ): HomeViewState {
        return when (event) {
            is HomeViewEvent.OnCategoriesLoaded -> {
                val updatedCatalog = event.categories.map { category ->
                    category.copy(
                        products = category.products.map { product ->
                            product.copy(
                                count = event.cartItems.firstOrNull { it.productId == product.id }?.quantity ?: 0,
                            )
                        },
                    )
                }
                state.copy(categories = updatedCatalog)
            }

            is HomeViewEvent.OnCategoryClicked -> {
                state.copy(
                    categories = state.categories.map {
                        it.copy(selected = it.id == event.categoryId)
                    },
                )
            }

            is HomeViewEvent.OnCartLoaded -> {
                val deliveryAddress = event.cartModel.deliveryAddress
                val deliveryType = event.cartModel.deliveryType

                val (addressString, deliveryInfo) = when (deliveryType) {
                    DeliveryType.PICKUP -> {
                        event.cartModel.department.name to "Самовывоз"
                    }

                    DeliveryType.DELIVERY -> {
                        buildString {
                            append(deliveryAddress?.street)
                            append(", ")
                            append(deliveryAddress?.house)
                        } to "Доставка ${event.cartModel.deliveryInfo.deliveryPrice.toInt()} руб"
                    }
                }

                val productsPrice = event.cartModel.items.sumOf { it.price * it.quantity }

                println("[HomeReducer.kt productPrice: $productsPrice")

                state.copy(
                    amount = event.cartModel.totalPrice,
                    productsPrice = productsPrice,
                    freeDeliveryPrice = event.cartModel.deliveryInfo.freeDeliveryPrice,
                    deliveryType = event.cartModel.deliveryType,
                    deliveryPrice = event.cartModel.deliveryInfo.deliveryPrice,
                    deliveryAddress = addressString,
                    cartDepartment = event.cartModel.department,
                    storeIsClosed = !event.cartModel.department.isWorkingNow,
                )
            }

            is HomeViewEvent.OnCurrentOrderLoaded -> {
                state.copy(
                    currentOrders = event.orders.map { orderUIModelMapper.toUIModel(it) },
                )
            }

            is HomeViewEvent.OnUserLoaded -> {
                state.copy(
                    userName = event.userModel?.name,
                )
            }

            else -> {
                state
            }
        }
    }

    override fun handleEvent(event: HomeViewEvent): HomeViewEffect? {
        return when (event) {
            else -> null
        }
    }
}