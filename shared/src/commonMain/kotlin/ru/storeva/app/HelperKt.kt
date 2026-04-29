package ru.storeva.app

import org.koin.core.context.startKoin
import ru.storeva.app.di.appIntroductionModule
import ru.storeva.app.di.appModule
import ru.storeva.app.di.authorizationModule
import ru.storeva.app.di.cartModule
import ru.storeva.app.di.catalogModule
import ru.storeva.app.di.currentOrderModule
import ru.storeva.app.di.dialogsModule
import ru.storeva.app.di.homeModule
import ru.storeva.app.di.mapModule
import ru.storeva.app.di.paymentModule
import ru.storeva.app.di.platformModule
import ru.storeva.app.di.profileModule
import ru.storeva.app.features.launch.di.launchModule

fun initKoin() {
    startKoin {
        modules(
            appModule(),
            launchModule,
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