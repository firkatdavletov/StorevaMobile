package ru.storeva.app.features.catalog

import ru.storeva.app.features.base.Reducer

interface CatalogViewEffect : Reducer.ViewEffect {
    data object None : CatalogViewEffect
}