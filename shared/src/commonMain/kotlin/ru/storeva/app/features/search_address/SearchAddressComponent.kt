package ru.storeva.app.features.search_address

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.features.base.BaseComponent

abstract class SearchAddressComponent(
    componentContext: ComponentContext,
    snackBarManager: SnackBarManager,
    initialState: SearchAddressViewState,
    reducer: SearchAddressReducer,
) : BaseComponent<SearchAddressViewState, SearchAddressViewEvent, SearchAddressViewEffect>(
        componentContext = componentContext,
        initialState = initialState,
        reducer = reducer,
        snackBarManager = snackBarManager,
    )