package ru.storeva.app.feature.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.storeva.app.features.profile.ProfileComponent
import ru.storeva.app.features.profile.ProfileViewEvent

@Composable
fun ProfileScreen(component: ProfileComponent) {
    val state by component.state.subscribeAsState()

    ProfileContent(
        name = state.name,
        phone = state.phone,
        isLoading = state.isLoading,
        onNameChanged = {
            component.onEvent(ProfileViewEvent.OnNameChanged(it))
        },
        onBack = {
            component.onEvent(ProfileViewEvent.OnBackClicked)
        },
        onLogout = {
            component.onEvent(ProfileViewEvent.OnLogout)
        },
        onSave = {
            component.onEvent(ProfileViewEvent.OnSave)
        },
        onDelete = {
            component.onEvent(ProfileViewEvent.OnDelete)
        },
    )
}