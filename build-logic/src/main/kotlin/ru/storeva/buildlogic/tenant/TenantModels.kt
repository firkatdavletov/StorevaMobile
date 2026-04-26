package ru.storeva.buildlogic.tenant

data class TenantConfig(
    val id: String,
    val app: TenantAppConfig,
    val api: TenantApiConfig,
    val branding: TenantBrandingConfig,
    val features: TenantFeaturesConfig,
) {
    fun apiBaseUrl(env: String): String {
        return when (env.lowercase()) {
            "prod", "production", "release" -> api.prodBaseUrl
            "stage", "staging" -> api.stageBaseUrl ?: api.prodBaseUrl
            "dev", "debug", "development" -> api.devBaseUrl ?: api.stageBaseUrl ?: api.prodBaseUrl
            else -> api.prodBaseUrl
        }
    }
}

data class TenantAppConfig(
    val displayName: String,
    val androidApplicationId: String,
    val iosBundleId: String,
    val deeplinkHost: String?,
)

data class TenantApiConfig(
    val devBaseUrl: String?,
    val stageBaseUrl: String?,
    val prodBaseUrl: String,
)

data class TenantBrandingConfig(
    val primaryColor: String,
    val accentColor: String,
    val backgroundColor: String,
    val cornerRadius: Int,
)

data class TenantFeaturesConfig(
    val payments: Boolean,
    val bonuses: Boolean,
    val pickupPoints: Boolean,
    val restaurantMode: Boolean,
    val tableOrders: Boolean,
    val waiterApp: Boolean,
)