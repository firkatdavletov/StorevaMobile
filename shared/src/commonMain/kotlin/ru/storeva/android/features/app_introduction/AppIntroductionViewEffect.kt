package ru.storeva.android.features.app_introduction

import ru.storeva.android.features.base.Reducer

sealed interface AppIntroductionViewEffect : Reducer.ViewEffect {
    data object None : AppIntroductionViewEffect
}