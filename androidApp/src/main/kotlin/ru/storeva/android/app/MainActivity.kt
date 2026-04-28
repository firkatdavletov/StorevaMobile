package ru.storeva.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.android.inject
import ru.storeva.android.data.datastore.local.AndroidSecurityStorage
import ru.storeva.android.data.datastore.local.SecurityStorage
import ru.storeva.android.features.SnackBarManager
import ru.storeva.android.navigation.DefaultRootComponent

class MainActivity : ComponentActivity() {
    val securityStorage: SecurityStorage by inject<SecurityStorage>()
    val snackBarManager: SnackBarManager by inject<SnackBarManager>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (securityStorage as AndroidSecurityStorage).initialize(this)
        MapKitFactory.initialize(applicationContext)
        val rootComponent = DefaultRootComponent(defaultComponentContext(), snackBarManager)
        enableEdgeToEdge()
        setContent {
            DeliveryApp(rootComponent = rootComponent)
        }
    }
}