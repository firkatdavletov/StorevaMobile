package ru.storeva.android.feature.authorization.verification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.storeva.android.features.authorization.verification_component.VerificationComponent
import ru.storeva.android.features.authorization.verification_component.VerifyViewEvent

@Composable
fun VerificationScreen(component: VerificationComponent) {
    val state by component.state.subscribeAsState()

    VerificationContent(
        code = state.code,
        onCodeChanged = {
            component.onEvent(VerifyViewEvent.OnCodeChanged(it))
        },
    )
}