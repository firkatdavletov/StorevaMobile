package ru.storeva.android.features.main_tabs.sbp_banks

import ru.storeva.android.domain.models.BankInfoModel
import ru.storeva.android.features.base.Reducer

data class SbpBanksViewState(
    val isLoading: Boolean,
    val banks: List<BankInfoModel>,
    val qrLink: String,
) : Reducer.ViewState