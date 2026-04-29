package ru.storeva.app.di

import org.koin.mp.KoinPlatform.getKoin
import ru.storeva.app.core.snackbar.SnackBarManager

object SnackBarManagerProvider {
    fun getSnackBarManager(): SnackBarManager = getKoin().get<SnackBarManager>()
}