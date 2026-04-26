package ru.storeva.android.features.authorization.sign_in_component

import ru.storeva.android.domain.models.AuthTypeModel
import ru.storeva.android.features.base.Reducer

data class SignInViewState(
    val authTypes: List<AuthTypeModel>,
    val phoneNumber: String,
    val isLoading: Boolean,
    val isError: Boolean,
    val confirmEnabled: Boolean,
    val selectedAuthType: String,
    val alert: String? = null,
) : Reducer.ViewState