package ru.storeva.android.features.utils

import ru.storeva.android.domain.models.AddressModel

object AddressUtility {

    fun addressString(model: AddressModel): String {
        return buildString {
            append(model.street)
            append(", ")
            append(model.house)
        }
    }
}