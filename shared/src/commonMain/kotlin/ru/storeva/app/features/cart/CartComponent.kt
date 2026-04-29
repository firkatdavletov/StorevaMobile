package ru.storeva.app.features.cart

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.base.BaseComponent

abstract class CartComponent(
    componentContext: ComponentContext,
    initialState: CartViewState,
    snackBarManager: SnackBarManager,
    reducer: CartReducer,
) : BaseComponent<CartViewState, CartViewEvent, CartViewEffect>(
        componentContext = componentContext,
        initialState = initialState,
        reducer = reducer,
        snackBarManager = snackBarManager,
    )