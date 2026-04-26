package ru.storeva.android

import org.koin.core.context.startKoin
import ru.storeva.android.di.appIntroductionModule
import ru.storeva.android.di.appModule
import ru.storeva.android.di.authorizationModule
import ru.storeva.android.di.cartModule
import ru.storeva.android.di.catalogModule
import ru.storeva.android.di.currentOrderModule
import ru.storeva.android.di.dialogsModule
import ru.storeva.android.di.homeModule
import ru.storeva.android.di.launchModule
import ru.storeva.android.di.mapModule
import ru.storeva.android.di.paymentModule
import ru.storeva.android.di.platformModule
import ru.storeva.android.di.profileModule

fun initKoin() {
    startKoin {
        modules(
            appModule(),
            launchModule(),
            mapModule(),
            homeModule(),
            cartModule(),
            paymentModule(),
            appIntroductionModule(),
            authorizationModule(),
            platformModule(),
            currentOrderModule(),
            catalogModule(),
            profileModule(),
            dialogsModule(),
        )
    }
}