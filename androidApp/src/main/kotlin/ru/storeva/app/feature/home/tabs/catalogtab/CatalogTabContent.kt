package ru.storeva.app.feature.home.tabs.catalogtab

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.storeva.app.features.catalog.CatalogTabComponent

@Composable
fun CatalogTabContent(
    component: CatalogTabComponent,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(
            text = "Catalog Tab",
        )
    }
}