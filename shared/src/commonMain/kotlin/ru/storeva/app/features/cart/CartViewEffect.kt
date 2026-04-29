package ru.storeva.app.features.cart

import ru.storeva.app.features.base.Reducer

sealed interface CartViewEffect : Reducer.ViewEffect {
    data object None : CartViewEffect
}