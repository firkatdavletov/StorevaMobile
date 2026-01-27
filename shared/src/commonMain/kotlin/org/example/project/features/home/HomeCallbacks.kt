package org.example.project.features.home

import org.example.project.domain.models.ProductModel

data class HomeCallbacks(
    val navigateToMap: () -> Unit,
    val showProductCard: (product: ProductModel) -> Unit,
    val navigateToCart: () -> Unit,
    val navigateToProfile: () -> Unit,
    val navigateToOrder: (Long) -> Unit,
    val navigateToAuthorization: () -> Unit,
)