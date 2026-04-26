package ru.storeva.android.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class DialogConfig {
    data class ProductCard(val productId: Long) : DialogConfig()

    data object DeleteUser : DialogConfig()

    data object LogoutUser : DialogConfig()
}