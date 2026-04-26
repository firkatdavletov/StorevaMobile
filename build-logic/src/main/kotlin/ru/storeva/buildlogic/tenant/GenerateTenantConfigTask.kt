package ru.storeva.buildlogic.tenant

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

abstract class GenerateTenantConfigTask : DefaultTask() {

    @get:InputFile
    abstract val tenantFile: RegularFileProperty

    @get:Input
    abstract val environment: Property<String>

    @get:Input
    abstract val generatedPackage: Property<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun generate() {
        val config = TenantConfigLoader.load(tenantFile.get().asFile)
        val env = environment.get()
        val packageName = generatedPackage.get()

        val packageDir = packageName.replace(".", "/")
        val outputFile = outputDir.file("$packageDir/BuildTenantConfig.kt").get().asFile

        outputFile.parentFile.mkdirs()
        outputFile.writeText(
            buildString {
                appendLine("package $packageName")
                appendLine()
                appendLine("object BuildTenantConfig {")
                appendLine("    const val TENANT_ID = ${config.id.kotlinString()}")
                appendLine("    const val ENVIRONMENT = ${env.kotlinString()}")
                appendLine("    const val APP_NAME = ${config.app.displayName.kotlinString()}")
                appendLine("    const val API_BASE_URL = ${config.apiBaseUrl(env).kotlinString()}")
                appendLine()
                appendLine("    const val PRIMARY_COLOR = ${config.branding.primaryColor.kotlinString()}")
                appendLine("    const val ACCENT_COLOR = ${config.branding.accentColor.kotlinString()}")
                appendLine("    const val BACKGROUND_COLOR = ${config.branding.backgroundColor.kotlinString()}")
                appendLine("    const val CORNER_RADIUS = ${config.branding.cornerRadius}")
                appendLine()
                appendLine("    const val PAYMENTS_ENABLED = ${config.features.payments}")
                appendLine("    const val BONUSES_ENABLED = ${config.features.bonuses}")
                appendLine("    const val PICKUP_POINTS_ENABLED = ${config.features.pickupPoints}")
                appendLine("    const val RESTAURANT_MODE_ENABLED = ${config.features.restaurantMode}")
                appendLine("    const val TABLE_ORDERS_ENABLED = ${config.features.tableOrders}")
                appendLine("    const val WAITER_APP_ENABLED = ${config.features.waiterApp}")
                appendLine("}")
            },
        )
    }
}

internal fun String.kotlinString(): String {
    return "\"" + this
        .replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("\n", "\\n") + "\""
}