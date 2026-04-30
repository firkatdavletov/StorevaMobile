package ru.storeva.app.feature.home.tabs.maintab

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.storeva.app.features.catalog.CatalogTabComponent
import ru.storeva.app.features.maintab.MainTabComponent

@Composable
fun MainTabContent(
    component: MainTabComponent,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(
            text = "Main Tab",
        )
    }
}