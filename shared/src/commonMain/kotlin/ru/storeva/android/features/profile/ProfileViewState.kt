package ru.storeva.android.features.profile

import ru.storeva.android.features.base.Reducer

data class ProfileViewState(
    val name: String,
    val phone: String,
    val isLoading: Boolean,
) : Reducer.ViewState