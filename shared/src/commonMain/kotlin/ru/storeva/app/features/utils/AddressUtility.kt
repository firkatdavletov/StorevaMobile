package ru.storeva.app.features.utils

import ru.storeva.app.domain.models.AddressModel

object AddressUtility {

    fun addressString(model: AddressModel): String {
        return buildString {
            append(model.street)
            append(", ")
            append(model.house)
        }
    }
}