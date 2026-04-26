package ru.storeva.android.di

import com.arkivanov.decompose.ComponentContext
import org.koin.dsl.module
import ru.storeva.android.features.catalog.CatalogCallbacks
import ru.storeva.android.features.catalog.CatalogComponent
import ru.storeva.android.features.catalog.DefaultCatalogComponent

fun catalogModule() =
    module {
        factory<CatalogComponent> { parameters ->
            val componentContext = parameters.component1<ComponentContext>()
            val categoryId = parameters.component2<Long>()
            val title = parameters.component3<String>()
            val callbacks = parameters.component4<CatalogCallbacks>()
            DefaultCatalogComponent(
                componentContext = componentContext,
                categoryId = categoryId,
                title = title,
                callbacks = callbacks,
                getProductsUseCase = get(),
                addToCartUseCase = get(),
                removeFromCartUseCase = get(),
                cartRepository = get(),
            )
        }
    }