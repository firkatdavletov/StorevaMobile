package ru.storeva.app.features.authorization.verification_component

import ru.storeva.app.features.base.Reducer

sealed interface VerifyViewEffect : Reducer.ViewEffect {
    data object None : VerifyViewEffect
}