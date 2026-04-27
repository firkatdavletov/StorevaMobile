package ru.storeva.buildlogic.tenant

import org.yaml.snakeyaml.Yaml
import java.io.File

object TenantConfigLoader {

    fun load(file: File): TenantConfig {
        require(file.exists()) {
            "Tenant config not found: ${file.absolutePath}"
        }

        val root = file.inputStream().use { input ->
            @Suppress("UNCHECKED_CAST")
            Yaml().load(input) as? Map<String, Any?>
        } ?: error("Invalid tenant yaml: ${file.absolutePath}")

        val androidApp = root.obj("androidApp")
        val iosApp = root.obj("iosApp")
        val api = root.obj("api")
        val branding = root.obj("branding")
        val features = root.obj("features")

        return TenantConfig(
            id = root.string("id"),
            androidApp = TenantAndroidAppConfig(
                applicationId = androidApp.string("applicationId"),
                appName = androidApp.string("appName"),
            ),
            iosApp = TenantIosAppConfig(
                bundleId = iosApp.string("bundleId"),
                displayName = iosApp.string("displayName"),
                deepLinkHost = iosApp.string("deepLinkHost"),
                appIconSet = iosApp.string("appIconSet"),
            ),
            api = TenantApiConfig(
                devBaseUrl = api.optionalString("devBaseUrl"),
                stageBaseUrl = api.optionalString("stageBaseUrl"),
                prodBaseUrl = api.string("prodBaseUrl"),
            ),
            branding = TenantBrandingConfig(
                primaryColor = branding.string("primaryColor", "#111111"),
                accentColor = branding.string("accentColor", "#000000"),
                backgroundColor = branding.string("backgroundColor", "#FFFFFF"),
                cornerRadius = branding.int("cornerRadius", 12),
            ),
            features = TenantFeaturesConfig(
                payments = features.bool("payments", false),
                bonuses = features.bool("bonuses", false),
                pickupPoints = features.bool("pickupPoints", false),
                restaurantMode = features.bool("restaurantMode", false),
                tableOrders = features.bool("tableOrders", false),
                waiterApp = features.bool("waiterApp", false),
            ),
        )
    }
}

@Suppress("UNCHECKED_CAST")
private fun Map<String, Any?>.obj(key: String): Map<String, Any?> {
    return this[key] as? Map<String, Any?> ?: emptyMap()
}

private fun Map<String, Any?>.string(
    key: String,
    defaultValue: String? = null,
): String {
    val value = this[key]

    return when {
        value is String -> value
        value != null -> value.toString()
        defaultValue != null -> defaultValue
        else -> error("Required yaml field is missing: $key")
    }
}

private fun Map<String, Any?>.optionalString(key: String): String? {
    val value = this[key] ?: return null
    return value.toString()
}

private fun Map<String, Any?>.bool(
    key: String,
    defaultValue: Boolean,
): Boolean {
    val value = this[key] ?: return defaultValue

    return when (value) {
        is Boolean -> value
        is String -> value.toBooleanStrictOrNull() ?: defaultValue
        else -> defaultValue
    }
}

private fun Map<String, Any?>.int(
    key: String,
    defaultValue: Int,
): Int {
    val value = this[key] ?: return defaultValue

    return when (value) {
        is Int -> value
        is Number -> value.toInt()
        is String -> value.toIntOrNull() ?: defaultValue
        else -> defaultValue
    }
}