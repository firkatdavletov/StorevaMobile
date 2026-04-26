package ru.storeva.android.features.app_introduction

import com.arkivanov.decompose.ComponentContext
import ru.storeva.android.features.base.BaseComponent

abstract class AppIntroductionComponent(
    componentContext: ComponentContext,
    initialState: AppIntroductionViewState,
    reducer: AppIntroductionReducer,
) : BaseComponent<AppIntroductionViewState, AppIntroductionViewEvent, AppIntroductionViewEffect>(
        componentContext = componentContext,
        reducer = reducer,
        initialState = initialState,
    )