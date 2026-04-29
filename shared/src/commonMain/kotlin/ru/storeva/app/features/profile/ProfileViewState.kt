package ru.storeva.app.features.profile

import ru.storeva.app.features.base.Reducer

data class ProfileViewState(
    val name: String,
    val phone: String,
    val isLoading: Boolean,
) : Reducer.ViewState