package ru.storeva.android.di

import org.koin.mp.KoinPlatform.getKoin
import ru.storeva.android.features.SnackBarManager

object SnackBarManagerProvider {
    fun getSnackBarManager(): SnackBarManager = getKoin().get<SnackBarManager>()
}