package ru.storeva.app.features.main_tabs.sbp_banks

import ru.storeva.app.domain.models.BankInfoModel
import ru.storeva.app.features.base.Reducer

sealed interface SbpBanksViewEvent : Reducer.ViewEvent {
    data object OnBackClicked : SbpBanksViewEvent

    data class OnBankClicked(val schema: String) : SbpBanksViewEvent

    data class OnBanksLoaded(val banks: List<BankInfoModel>) : SbpBanksViewEvent
}