package ru.storeva.android.features.payment

import com.arkivanov.decompose.ComponentContext
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.base.BaseComponent

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