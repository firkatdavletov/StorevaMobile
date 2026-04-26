package ru.storeva.android.features.cart

import com.arkivanov.decompose.ComponentContext
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.base.BaseComponent

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