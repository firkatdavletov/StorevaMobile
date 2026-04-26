package ru.storeva.android.domain.utils

import ru.storeva.android.domain.models.AddressModel

object AddressUtility {
    fun makeAddressString(model: AddressModel) =
        buildString {
            append(model.street)
            append(", ")
            append(model.house)
        }
}