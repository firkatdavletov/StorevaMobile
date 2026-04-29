package ru.storeva.app.features.payment

import ru.storeva.app.features.base.Reducer

sealed interface PaymentViewEffect : Reducer.ViewEffect {
    data object None : PaymentViewEffect
}