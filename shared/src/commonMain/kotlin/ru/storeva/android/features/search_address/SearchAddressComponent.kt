package ru.storeva.android.features.search_address

import com.arkivanov.decompose.ComponentContext
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.base.BaseComponent

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