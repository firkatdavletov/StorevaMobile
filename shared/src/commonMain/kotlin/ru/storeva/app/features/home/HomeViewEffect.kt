package ru.storeva.app.features.home

import ru.storeva.app.features.base.Reducer

interface HomeViewEffect : Reducer.ViewEffect {
    data object None : HomeViewEffect
}