package ru.storeva.app.feature.home.tabs.profiletab

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.storeva.app.features.catalog.CatalogTabComponent
import ru.storeva.app.features.profile.ProfileTabComponent

@Composable
fun ProfileTabContent(
    component: ProfileTabComponent,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Text(
            text = "Profile Tab",
        )
    }
}