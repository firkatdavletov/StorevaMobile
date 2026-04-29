package ru.storeva.app.features.authorization.verification_component

import ru.storeva.app.features.base.Reducer

data class VerifyViewState(
    val isLoading: Boolean,
    val authType: String,
    val callPhone: String?,
    val phoneNumber: String,
    val code: String,
    val confirmEnabled: Boolean,
    val alert: String? = null,
) : Reducer.ViewState