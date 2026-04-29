package ru.storeva.app.features.catalog

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.features.base.BaseComponent

abstract class CatalogComponent(
    componentContext: ComponentContext,
    initialState: CatalogViewState,
    reducer: CatalogReducer,
) : BaseComponent<CatalogViewState, CatalogViewEvent, CatalogViewEffect>(
        componentContext = componentContext,
        initialState = initialState,
        reducer = reducer,
    )