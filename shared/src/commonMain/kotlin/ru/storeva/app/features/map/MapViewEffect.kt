package ru.storeva.app.features.map

import ru.storeva.app.features.base.Reducer

sealed interface MapViewEffect : Reducer.ViewEffect {
    data class ShowError(val message: String) : MapViewEffect
}