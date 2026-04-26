package ru.storeva.android.feature.authorization.sign_in

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.storeva.android.features.authorization.sign_in_component.SignInComponent
import ru.storeva.android.features.authorization.sign_in_component.SignInViewEvent

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SignInScreen(component: SignInComponent) {
    val state by component.state.subscribeAsState()

    BackHandler {
        component.onEvent(SignInViewEvent.OnBackClicked)
    }

    SignInContent(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        isLoading = state.isLoading,
        phoneNumber = state.phoneNumber,
        onPhoneNumberChanged = {
            component.onEvent(SignInViewEvent.OnPhoneNumberChanged(it))
        },
        authTypes = state.authTypes,
        onAuthTypeClicked = {
            component.onEvent(SignInViewEvent.AuthTypeClicked(it))
        },
    )
}