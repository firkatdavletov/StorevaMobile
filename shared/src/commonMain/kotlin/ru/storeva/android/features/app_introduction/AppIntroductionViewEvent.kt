package ru.storeva.android.features.app_introduction

import ru.storeva.android.features.base.Reducer

sealed interface AppIntroductionViewEvent : Reducer.ViewEvent {
    data object OnContinue : AppIntroductionViewEvent
}