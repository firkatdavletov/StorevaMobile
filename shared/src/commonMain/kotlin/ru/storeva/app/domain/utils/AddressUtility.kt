package ru.storeva.app.domain.utils

import ru.storeva.app.domain.models.AddressModel

object AddressUtility {
    fun makeAddressString(model: AddressModel) =
        buildString {
            append(model.street)
            append(", ")
            append(model.house)
        }
}