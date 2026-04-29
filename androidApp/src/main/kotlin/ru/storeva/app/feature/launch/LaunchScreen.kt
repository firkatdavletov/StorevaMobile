package ru.storeva.app.feature.launch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.storeva.app.features.launch.presentation.LaunchComponent
import ru.storeva.app.features.launch.presentation.LaunchEvent

@Composable
fun LaunchScreen(component: LaunchComponent) {
    val state by component.state.subscribeAsState()

    LaunchContent(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxSize(),
        isLoading = state.isLoading,
        isError = state.isError,
        onRetryClicked = {
            component.onEvent(LaunchEvent.RetryClicked)
        },
    )
}