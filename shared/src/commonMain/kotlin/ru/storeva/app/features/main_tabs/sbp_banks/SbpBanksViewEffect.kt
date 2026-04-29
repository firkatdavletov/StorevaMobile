package ru.storeva.app.features.main_tabs.sbp_banks

import ru.storeva.app.features.base.Reducer

sealed interface SbpBanksViewEffect : Reducer.ViewEffect {
    data object None : SbpBanksViewEffect
}