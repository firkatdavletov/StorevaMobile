package ru.storeva.android.features.authorization.verification_component

import ru.storeva.android.features.base.Reducer

sealed interface VerifyViewEffect : Reducer.ViewEffect {
    data object None : VerifyViewEffect
}