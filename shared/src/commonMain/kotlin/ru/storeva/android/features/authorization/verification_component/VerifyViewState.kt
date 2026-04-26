package ru.storeva.android.features.authorization.verification_component

import ru.storeva.android.features.base.Reducer

data class VerifyViewState(
    val isLoading: Boolean,
    val authType: String,
    val callPhone: String?,
    val phoneNumber: String,
    val code: String,
    val confirmEnabled: Boolean,
    val alert: String? = null,
) : Reducer.ViewState