package ru.storeva.app.features.map

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.base.BaseComponent

abstract class MapComponent(
    componentContext: ComponentContext,
    snackBarManager: SnackBarManager,
    initialState: MapViewState,
) : BaseComponent<MapViewState, MapViewEvent, MapViewEffect>(
        componentContext = componentContext,
        initialState = initialState,
        reducer = MapReducer(),
        snackBarManager = snackBarManager,
    ) {

    abstract override fun onEvent(event: MapViewEvent)
}