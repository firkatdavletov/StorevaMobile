package ru.storeva.android.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.storeva.android.features.map.DefaultMapComponent
import ru.storeva.android.features.map.MapCallbacks
import ru.storeva.android.features.map.MapComponent
import ru.storeva.android.features.search_address.DefaultSearchAddressComponent
import ru.storeva.android.features.search_address.SearchAddressCallbacks
import ru.storeva.android.features.search_address.SearchAddressComponent

fun mapModule() =
    module {
        factory<MapComponent> { (componentContext: ComponentContext, fromScreen: String?, callbacks: MapCallbacks) ->
            DefaultMapComponent(
                componentContext = componentContext,
                snackBarManager = get(),
                callbacks = callbacks,
                getGeoAddressUseCase = get(),
                updateDeliveryAddressUseCase = get(),
                cartRepository = get(),
                getDepartmentsUseCase = get(),
                createCartUseCase = get(),
                loadCartUseCase = get(),
                fromScreen = fromScreen,
            )
        }

        factory<SearchAddressComponent> { (componentContext: ComponentContext, callbacks: SearchAddressCallbacks, fromScreen: String?) ->
            DefaultSearchAddressComponent(
                componentContext = componentContext,
                snackBarManager = get(),
                fromScreen = fromScreen,
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                get(),
                callbacks,
            )
        }
    }