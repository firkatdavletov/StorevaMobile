package ru.storeva.app.features.payment

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.base.BaseComponent

abstract class PaymentComponent(
    componentContext: ComponentContext,
    initialState: PaymentViewState,
    snackBarManager: SnackBarManager,
) : BaseComponent<PaymentViewState, PaymentViewEvent, PaymentViewEffect>(
        componentContext = componentContext,
        initialState = initialState,
        reducer = PaymentReducer(),
        snackBarManager = snackBarManager,
    )