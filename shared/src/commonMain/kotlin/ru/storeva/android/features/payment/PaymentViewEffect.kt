package ru.storeva.android.features.payment

import ru.storeva.android.features.base.Reducer

sealed interface PaymentViewEffect : Reducer.ViewEffect {
    data object None : PaymentViewEffect
}