package ru.storeva.app.features.launch.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class AppConfigDto(
    val minSupportedVersion: String?,
    val maintenance: Map<String, String>?,
    val featureFlags: Map<String, String>?,
)