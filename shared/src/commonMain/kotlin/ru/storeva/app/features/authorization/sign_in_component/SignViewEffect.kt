package ru.storeva.app.features.authorization.sign_in_component

import ru.storeva.app.features.base.Reducer

sealed interface SignViewEffect : Reducer.ViewEffect {
    data object None : SignViewEffect
}