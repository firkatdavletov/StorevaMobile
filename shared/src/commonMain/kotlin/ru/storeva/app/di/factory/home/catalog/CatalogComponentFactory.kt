package ru.storeva.app.di.factory.home.catalog

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.features.catalog.CatalogTabComponent

fun interface CatalogComponentFactory {
    fun create(componentContext: ComponentContext): CatalogTabComponent
}