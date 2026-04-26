package ru.storeva.android.features.main_tabs.sbp_banks

import ru.storeva.android.domain.models.BankInfoModel
import ru.storeva.android.features.base.Reducer

sealed interface SbpBanksViewEvent : Reducer.ViewEvent {
    data object OnBackClicked : SbpBanksViewEvent

    data class OnBankClicked(val schema: String) : SbpBanksViewEvent

    data class OnBanksLoaded(val banks: List<BankInfoModel>) : SbpBanksViewEvent
}