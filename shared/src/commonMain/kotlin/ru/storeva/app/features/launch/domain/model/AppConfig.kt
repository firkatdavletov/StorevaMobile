package ru.storeva.app.features.launch.domain.model

data class AppConfig(
    val minSupportedVersion: String?,
    val maintenance: Map<String, String>?,
    val featureFlags: Map<String, String>?,
)