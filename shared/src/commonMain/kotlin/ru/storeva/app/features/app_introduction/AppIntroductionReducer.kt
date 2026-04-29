package ru.storeva.app.features.app_introduction

import ru.storeva.app.features.base.Reducer

class AppIntroductionReducer : Reducer<AppIntroductionViewState, AppIntroductionViewEvent, AppIntroductionViewEffect> {
    override fun reduce(
        state: AppIntroductionViewState,
        event: AppIntroductionViewEvent,
    ): AppIntroductionViewState {
        return when (event) {
            else -> {
                state
            }
        }
    }

    override fun handleEvent(event: AppIntroductionViewEvent): AppIntroductionViewEffect? {
        return when (event) {
            AppIntroductionViewEvent.OnContinue -> null
        }
    }
}