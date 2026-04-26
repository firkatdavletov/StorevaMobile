package ru.storeva.android.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.storeva.android.features.current_order.CurrentOrderCallbacks
import ru.storeva.android.features.current_order.CurrentOrderComponent
import ru.storeva.android.features.current_order.DefaultCurrentOrderComponent

fun currentOrderModule() =
    module {
        factory<CurrentOrderComponent> {
            (componentContext: ComponentContext, fromScreen: String?, callbacks: CurrentOrderCallbacks, orderId: Long),
            ->
            DefaultCurrentOrderComponent(componentContext, fromScreen, callbacks, get(), orderId, get())
        }
    }