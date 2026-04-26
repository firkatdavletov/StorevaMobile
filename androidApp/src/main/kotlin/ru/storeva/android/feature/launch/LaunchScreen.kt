package ru.storeva.android.feature.launch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.storeva.android.features.launch.DefaultLaunchComponent
import ru.storeva.android.features.launch.LaunchComponent
import ru.storeva.android.features.launch.LaunchViewEvent

@Composable
fun LaunchScreen(component: LaunchComponent) {
    val state by (component as DefaultLaunchComponent).state.subscribeAsState()

    LaunchContent(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxSize(),
        isLoading = state.isLoading,
        isError = state.isError,
    ) {
        component.onEvent(LaunchViewEvent.OnReconnect)
    }
}