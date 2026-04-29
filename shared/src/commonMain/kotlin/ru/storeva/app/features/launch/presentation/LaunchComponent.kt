package ru.storeva.app.features.launch.presentation

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.Flow

interface LaunchComponent {
    val state: Value<LaunchState>
    val effects: Flow<LaunchEffect>

    fun onEvent(event: LaunchEvent)
}