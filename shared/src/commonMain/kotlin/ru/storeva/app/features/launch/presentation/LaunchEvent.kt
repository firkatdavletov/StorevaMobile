package ru.storeva.app.features.launch.presentation

sealed interface LaunchEvent {
    data object RetryClicked : LaunchEvent
}