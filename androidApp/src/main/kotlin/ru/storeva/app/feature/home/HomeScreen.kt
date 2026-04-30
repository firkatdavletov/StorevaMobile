package ru.storeva.app.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.storeva.app.R
import ru.storeva.app.feature.home.tabs.carttab.CartTabContent
import ru.storeva.app.feature.home.tabs.catalogtab.CatalogTabContent
import ru.storeva.app.feature.home.tabs.maintab.MainTabContent
import ru.storeva.app.feature.home.tabs.profiletab.ProfileTabContent
import ru.storeva.app.features.home.presentation.HomeComponent
import ru.storeva.app.features.home.presentation.HomeTab

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    component: HomeComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.state.subscribeAsState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = state.selectedTab == HomeTab.Main,
                    onClick = { component.onTabSelected(HomeTab.Main) },
                    icon = { Icon(ImageVector.vectorResource(R.drawable.ic_plus_2_24), contentDescription = null) },
                    label = { Text("Каталог") },
                )

                NavigationBarItem(
                    selected = state.selectedTab == HomeTab.Catalog,
                    onClick = { component.onTabSelected(HomeTab.Catalog) },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (state.cartBadgeCount > 0) {
                                    Badge {
                                        Text(state.cartBadgeCount.toString())
                                    }
                                }
                            },
                        ) {
                            Icon(ImageVector.vectorResource(R.drawable.ic_plus_2_24), contentDescription = null)
                        }
                    },
                    label = { Text("Корзина") },
                )

                NavigationBarItem(
                    selected = state.selectedTab == HomeTab.Cart,
                    onClick = { component.onTabSelected(HomeTab.Cart) },
                    icon = { Icon(ImageVector.vectorResource(R.drawable.ic_plus_2_24), contentDescription = null) },
                    label = { Text("Профиль") },
                )

                NavigationBarItem(
                    selected = state.selectedTab == HomeTab.Profile,
                    onClick = { component.onTabSelected(HomeTab.Profile) },
                    icon = { Icon(ImageVector.vectorResource(R.drawable.ic_person_24), contentDescription = null) },
                    label = { Text("Профиль") },
                )
            }
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            when (state.selectedTab) {
                HomeTab.Catalog -> {
                    CatalogTabContent(component.catalogComponent)
                }

                HomeTab.Cart -> {
                    CartTabContent(component.cartComponent)
                }

                HomeTab.Main -> {
                    MainTabContent(component.mainComponent)
                }

                HomeTab.Profile -> {
                    ProfileTabContent(component.profileComponent)
                }
            }
        }
    }
}