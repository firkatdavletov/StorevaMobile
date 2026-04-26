package ru.storeva.android.features.authorization.sign_in_component

import ru.storeva.android.features.base.Reducer

sealed interface SignViewEffect : Reducer.ViewEffect {
    data object None : SignViewEffect
}