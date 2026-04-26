package ru.storeva.android.features.cart

import ru.storeva.android.features.base.Reducer

sealed interface CartViewEffect : Reducer.ViewEffect {
    data object None : CartViewEffect
}