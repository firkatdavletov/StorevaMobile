package ru.storeva.app.features.profile

import ru.storeva.app.features.base.Reducer

sealed interface ProfileViewEffect : Reducer.ViewEffect {
    data class ShowError(val message: String?) : ProfileViewEffect
}