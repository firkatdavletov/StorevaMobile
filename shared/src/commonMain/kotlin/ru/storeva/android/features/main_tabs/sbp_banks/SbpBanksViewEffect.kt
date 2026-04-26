package ru.storeva.android.features.main_tabs.sbp_banks

import ru.storeva.android.features.base.Reducer

sealed interface SbpBanksViewEffect : Reducer.ViewEffect {
    data object None : SbpBanksViewEffect
}