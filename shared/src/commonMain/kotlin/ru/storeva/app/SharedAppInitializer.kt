package ru.storeva.app

import com.arkivanov.decompose.ComponentContext
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin
import ru.storeva.app.core.snackbar.SnackBarManager
import ru.storeva.app.di.appIntroductionModule
import ru.storeva.app.di.appModule
import ru.storeva.app.di.authorizationModule
import ru.storeva.app.di.cartModule
import ru.storeva.app.di.catalogModule
import ru.storeva.app.di.currentOrderModule
import ru.storeva.app.di.dialogsModule
import ru.storeva.app.di.factory.root.RootComponentFactory
import ru.storeva.app.di.homeModule
import ru.storeva.app.di.mapModule
import ru.storeva.app.di.paymentModule
import ru.storeva.app.di.platformModule
import ru.storeva.app.di.profileModule
import ru.storeva.app.navigation.RootComponent

object SharedAppInitializer {
    fun initKoin() {
        startKoin {
            modules(
                appModule(),
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

    fun createRootComponent(
        componentContext: ComponentContext,
        snackBarManager: SnackBarManager,
    ): RootComponent {
        return getKoin()
            .get<RootComponentFactory>()
            .create(
                componentContext = componentContext,
                snackBarManager = snackBarManager,
            )
    }
}