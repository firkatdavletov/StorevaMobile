package ru.storeva.app.di.factory.root

import com.arkivanov.decompose.ComponentContext
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.navigation.RootComponent

fun interface RootComponentFactory {

    fun create(
        componentContext: ComponentContext,
        snackBarManager: SnackBarManager,
    ): RootComponent
}