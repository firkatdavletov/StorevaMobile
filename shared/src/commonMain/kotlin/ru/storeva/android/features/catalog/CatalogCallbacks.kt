package ru.storeva.android.features.catalog

data class CatalogCallbacks(
    val onBack: () -> Unit,
    val onNavigateToCart: () -> Unit,
    val showProductCard: (Long) -> Unit,
)