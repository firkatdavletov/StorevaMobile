package ru.storeva.android.feature.map_view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.storeva.android.core.util.GeolocationService
import ru.storeva.android.features.map.DefaultMapComponent
import ru.storeva.android.features.map.MapComponent
import ru.storeva.android.features.map.MapViewEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(component: MapComponent) {
    val context = LocalContext.current
    val state by (component as DefaultMapComponent).state.subscribeAsState()

    BackHandler {
        component.onEvent(MapViewEvent.OnBackClicked)
    }

    LocationPermissionRequest(
        onPermissionGranted = {
            GeolocationService.getCurrentLocationWithManager(context) { location ->
                if (location != null) {
                    component.onEvent(
                        MapViewEvent.OnMoveToLocation(
                            latitude = location.latitude,
                            longitude = location.longitude,
                        ),
                    )
                }
            }
        },
    )

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { scaffoldPadding ->
        MapContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding),
            addressString = state.deliveryAddress,
            deliveryInfo = state.deliveryPrice.toString(),
            isLoading = state.isLoading,
            isSearching = state.isSearching,
            isConfirmEnabled = state.confirmEnabled,
            location = state.currentPosition,
            moveToLocation = state.showLocation,
            deliveryType = state.deliveryType,
            onSelectDeliveryType = {
                component.onEvent(MapViewEvent.OnChangeDeliveryType(it))
            },
            onConfirm = {
                component.onEvent(MapViewEvent.OnConfirm)
            },
            onBackButtonClicked = {
                component.onEvent(MapViewEvent.OnBackClicked)
            },
            onMapMoved = { lat, lng, reason, finished ->
                component.onEvent(MapViewEvent.OnMapMoved(lat, lng, reason, finished))
            },
            onSelectDepartment = {
                component.onEvent(MapViewEvent.OnDepartmentSelected(it))
            },
            onRequestLocation = {
                GeolocationService.getCurrentLocationWithManager(context) { location ->
                    if (location != null) {
                        component.onEvent(
                            MapViewEvent.OnMoveToLocation(
                                latitude = location.latitude,
                                longitude = location.longitude,
                            ),
                        )
                    }
                }
            },
            onSearchClicked = {
                component.onEvent(MapViewEvent.OnSearchAddressClicked)
            },
            selectedDepartment = state.selectedDepartment,
            departments = state.departments,
            showBackButton = state.showBackButton,
        )
    }
}