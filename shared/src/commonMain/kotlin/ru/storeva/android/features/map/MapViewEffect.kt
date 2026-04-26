package ru.storeva.android.features.map

import ru.storeva.android.features.base.Reducer

sealed interface MapViewEffect : Reducer.ViewEffect {
    data class ShowError(val message: String) : MapViewEffect
}