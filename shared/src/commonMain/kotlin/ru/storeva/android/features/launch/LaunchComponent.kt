package ru.storeva.android.features.launch

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.features.base.BaseComponent

abstract class LaunchComponent(
    initialState: LaunchViewState,
    reducer: LaunchReducer,
    componentContext: ComponentContext,
    snackBarManager: SnackBarManager,
) : BaseComponent<LaunchViewState, LaunchViewEvent, LaunchViewEffect>(
        componentContext,
        initialState,
        reducer,
        snackBarManager,
    ) {
    abstract override fun onEvent(event: LaunchViewEvent)

    override val state: Value<LaunchViewState>
        get() = super.state
}