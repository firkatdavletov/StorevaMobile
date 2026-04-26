package ru.storeva.android.features.search_address

import ru.storeva.android.features.base.Reducer

sealed interface SearchAddressViewEffect : Reducer.ViewEffect {
    data object None : SearchAddressViewEffect
}