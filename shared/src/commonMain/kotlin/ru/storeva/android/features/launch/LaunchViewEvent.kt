package ru.storeva.android.features.launch

import ru.storeva.android.features.base.Reducer

sealed interface LaunchViewEvent : Reducer.ViewEvent {
    data object OnReconnect : LaunchViewEvent

    data object OnError : LaunchViewEvent
}