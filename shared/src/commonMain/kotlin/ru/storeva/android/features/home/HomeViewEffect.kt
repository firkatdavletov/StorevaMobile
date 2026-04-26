package ru.storeva.android.features.home

import ru.storeva.android.features.base.Reducer

interface HomeViewEffect : Reducer.ViewEffect {
    data object None : HomeViewEffect
}