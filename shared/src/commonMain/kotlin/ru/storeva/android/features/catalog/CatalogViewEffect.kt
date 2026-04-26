package ru.storeva.android.features.catalog

import ru.storeva.android.features.base.Reducer

interface CatalogViewEffect : Reducer.ViewEffect {
    data object None : CatalogViewEffect
}