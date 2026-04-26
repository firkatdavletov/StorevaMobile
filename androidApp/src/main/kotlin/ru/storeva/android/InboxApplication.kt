package ru.storeva.android

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
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

class InboxApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
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
        MapKitFactory.setApiKey("ae6b93e5-52ca-4ab3-879c-34d8728b59b5")
    }
}