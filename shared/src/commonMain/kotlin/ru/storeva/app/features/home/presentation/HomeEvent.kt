package ru.storeva.app.features.home.presentation

sealed interface HomeEvent {
    data class TabSelected(val tab: HomeTab) : HomeEvent
}