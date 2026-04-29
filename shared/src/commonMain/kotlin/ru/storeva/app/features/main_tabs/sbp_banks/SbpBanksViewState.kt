package ru.storeva.app.features.main_tabs.sbp_banks

import ru.storeva.app.domain.models.BankInfoModel
import ru.storeva.app.features.base.Reducer

data class SbpBanksViewState(
    val isLoading: Boolean,
    val banks: List<BankInfoModel>,
    val qrLink: String,
) : Reducer.ViewState