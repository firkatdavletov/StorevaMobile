package ru.storeva.android.features.launch

import ru.storeva.android.features.base.Reducer

data class LaunchViewState(
    val isLoading: Boolean,
    val isError: Boolean,
) : Reducer.ViewState