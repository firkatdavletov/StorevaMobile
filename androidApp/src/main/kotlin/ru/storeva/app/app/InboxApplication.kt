package ru.storeva.app.app

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
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

class InboxApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
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
        MapKitFactory.setApiKey("ae6b93e5-52ca-4ab3-879c-34d8728b59b5")
    }
}