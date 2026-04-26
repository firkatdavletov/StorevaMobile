package ru.storeva.android.features.home

import ru.storeva.android.domain.models.ProductModel

data class HomeCallbacks(
    val navigateToMap: () -> Unit,
    val showProductCard: (product: ProductModel) -> Unit,
    val navigateToCart: () -> Unit,
    val navigateToProfile: () -> Unit,
    val navigateToOrder: (Long) -> Unit,
    val navigateToAuthorization: () -> Unit,
    val navigateToCatalog: (categoryId: Long, title: String) -> Unit,
)