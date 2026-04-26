package ru.storeva.android.features.authorization.verification_component

import ru.storeva.android.features.base.Reducer

sealed interface VerifyViewEvent : Reducer.ViewEvent {
    data class OnCodeChanged(val newValue: String) : VerifyViewEvent

    data class OnError(val message: String?) : VerifyViewEvent

    data class OnThrowError(val throwable: Throwable) : VerifyViewEvent

    data object OnBackClicked : VerifyViewEvent

    data object OnCallPhoneClicked : VerifyViewEvent

    data object OnAppBecameActive : VerifyViewEvent
}