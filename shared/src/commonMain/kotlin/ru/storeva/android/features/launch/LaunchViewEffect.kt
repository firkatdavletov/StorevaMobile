package ru.storeva.android.features.launch

import ru.storeva.android.features.base.Reducer

sealed interface LaunchViewEffect : Reducer.ViewEffect {
    data class ShowError(val message: String) : LaunchViewEffect
}