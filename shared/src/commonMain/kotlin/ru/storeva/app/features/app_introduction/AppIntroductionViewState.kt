package ru.storeva.app.features.app_introduction

import ru.storeva.app.features.base.Reducer

data class AppIntroductionViewState(
    val title: String,
) : Reducer.ViewState