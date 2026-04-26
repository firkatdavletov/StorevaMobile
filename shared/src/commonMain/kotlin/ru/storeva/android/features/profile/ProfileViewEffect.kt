package ru.storeva.android.features.profile

import ru.storeva.android.features.base.Reducer

sealed interface ProfileViewEffect : Reducer.ViewEffect {
    data class ShowError(val message: String?) : ProfileViewEffect
}