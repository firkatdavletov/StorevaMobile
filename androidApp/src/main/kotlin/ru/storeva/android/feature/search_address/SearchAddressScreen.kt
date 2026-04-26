package ru.storeva.android.feature.search_address

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.storeva.android.features.search_address.SearchAddressComponent
import ru.storeva.android.features.search_address.SearchAddressViewEvent

@Composable
fun SearchAddressScreen(component: SearchAddressComponent) {
    val state by component.state.subscribeAsState()

    SearchAddressContent(
        deliveryType = state.deliveryType,
        isLoading = state.isLoading,
        isSearching = state.isSearching,
        query = state.query,
        departments = state.departments,
        addresses = state.addresses,
        onQueryChanged = {
            component.onEvent(SearchAddressViewEvent.OnQueryChanged(it))
        },
        onSelectAddress = {
            component.onEvent(SearchAddressViewEvent.OnAddressClicked(it))
        },
        onBackClicked = {
            component.onEvent(SearchAddressViewEvent.OnBackClicked)
        },
        onMapClicked = {
            component.onEvent(SearchAddressViewEvent.OnMapClicked)
        },
    )
}