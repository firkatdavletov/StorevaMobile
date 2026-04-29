package ru.storeva.app.features.main_tabs.sbp_banks

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.features.base.BaseComponent

abstract class SbpBanksComponent(
    componentContext: ComponentContext,
    initialState: SbpBanksViewState,
    initialEffect: SbpBanksViewEffect.None,
    reducer: SbpBanksReducer,
) : BaseComponent<SbpBanksViewState, SbpBanksViewEvent, SbpBanksViewEffect>(
        componentContext = componentContext,
        initialState = initialState,
        reducer = reducer,
    )