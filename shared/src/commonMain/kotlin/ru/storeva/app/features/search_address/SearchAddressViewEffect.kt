package ru.storeva.app.features.search_address

import ru.storeva.app.features.base.Reducer

sealed interface SearchAddressViewEffect : Reducer.ViewEffect {
    data object None : SearchAddressViewEffect
}