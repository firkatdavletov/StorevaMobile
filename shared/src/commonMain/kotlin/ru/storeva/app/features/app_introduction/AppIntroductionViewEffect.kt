package ru.storeva.app.features.app_introduction

import ru.storeva.app.features.base.Reducer

sealed interface AppIntroductionViewEffect : Reducer.ViewEffect {
    data object None : AppIntroductionViewEffect
}