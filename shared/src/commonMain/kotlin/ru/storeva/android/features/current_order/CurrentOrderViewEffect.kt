package ru.storeva.android.features.current_order

import ru.storeva.android.features.base.Reducer

sealed interface CurrentOrderViewEffect : Reducer.ViewEffect {
    data object None : CurrentOrderViewEffect
}