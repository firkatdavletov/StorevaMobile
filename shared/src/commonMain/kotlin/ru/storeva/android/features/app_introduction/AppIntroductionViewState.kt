package ru.storeva.android.features.app_introduction

import ru.storeva.android.features.base.Reducer

data class AppIntroductionViewState(
    val title: String,
) : Reducer.ViewState