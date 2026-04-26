package ru.storeva.android.di

import com.arkivanov.decompose.ComponentContext
import org.koin.core.module.Module
import org.koin.dsl.module
import ru.storeva.android.domain.repositories.CartRepository
import ru.storeva.android.domain.usecase.payment.GetPaymentTypesUseCase
import ru.storeva.android.features.payment.DefaultPaymentComponent
import ru.storeva.android.features.payment.PaymentCallbacks
import ru.storeva.android.features.payment.PaymentComponent

fun paymentModule(): Module =
    module {
        single<PaymentComponent> { (componentContext: ComponentContext, callbacks: PaymentCallbacks) ->
            DefaultPaymentComponent(
                componentContext = componentContext,
                callbacks = callbacks,
                cartRepository = get<CartRepository>(),
                getPaymentTypesUseCase = get<GetPaymentTypesUseCase>(),
                createOrderUseCase = get(),
                updateCartAddressUseCase = get(),
                clearCartUseCase = get(),
                snackBarManager = get(),
            )
        }
    }