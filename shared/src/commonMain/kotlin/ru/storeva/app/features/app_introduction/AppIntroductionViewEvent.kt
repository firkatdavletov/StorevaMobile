package ru.storeva.app.features.app_introduction

import ru.storeva.app.features.base.Reducer

sealed interface AppIntroductionViewEvent : Reducer.ViewEvent {
    data object OnContinue : AppIntroductionViewEvent
}