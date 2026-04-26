package ru.storeva.android.features.dialogs.product_card

import com.arkivanov.decompose.ComponentContext
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.base.BaseComponent

abstract class ProductCardComponent(
    componentContent: ComponentContext,
    initialState: ProductCardViewState,
    reducer: ProductCardReducer,
    snackBarManager: SnackBarManager,
) : BaseComponent<ProductCardViewState, ProductCardViewEvent, ProductCardViewEffect>(
        componentContext = componentContent,
        initialState = initialState,
        reducer = reducer,
        snackBarManager = snackBarManager,
    )