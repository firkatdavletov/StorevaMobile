package ru.storeva.app.features.current_order

import ru.storeva.app.features.base.Reducer

sealed interface CurrentOrderViewEffect : Reducer.ViewEffect {
    data object None : CurrentOrderViewEffect
}