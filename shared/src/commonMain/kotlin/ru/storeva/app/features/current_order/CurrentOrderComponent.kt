package ru.storeva.app.features.current_order

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.features.base.BaseComponent

abstract class CurrentOrderComponent(
    componentContext: ComponentContext,
    initialState: CurrentOrderViewState,
) : BaseComponent<CurrentOrderViewState, CurrentOrderViewEvent, CurrentOrderViewEffect>(
        componentContext = componentContext,
        initialState = initialState,
        reducer = CurrentOrderReducer(),
    )