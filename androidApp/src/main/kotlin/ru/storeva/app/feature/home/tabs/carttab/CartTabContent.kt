package ru.storeva.app.feature.home.tabs.carttab

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.storeva.app.features.cart.CartTabComponent
import ru.storeva.app.features.maintab.MainTabComponent

@Composable
fun CartTabContent(
    component: CartTabComponent,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(
            text = "Cart Tab",
        )
    }
}