package ru.storeva.buildlogic.tenant

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class TenantPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val extension = project.extensions.create(
            "tenantConfig",
            TenantExtension::class.java,
        )

        val tenantId = project.providers
            .gradleProperty("tenant")
            .orElse("demo")
            .get()

        val env = project.providers
            .gradleProperty("env")
            .orElse("dev")
            .get()

        val tenantDir = project.rootProject.layout.projectDirectory
            .dir("tenants/$tenantId")
        val tenantConfigFile = tenantDir.file("tenant.yaml")

        val generateTenantConfigTask = project.tasks.register(
            "generateTenantConfig",
            GenerateTenantConfigTask::class.java,
        ) {
            group = "tenant"
            description = "Generates Kotlin tenant config for shared/commonMain"

            this.tenantFile.set(tenantConfigFile)
            environment.set(env)
            generatedPackage.set(extension.generatedPackage)
            outputDir.set(
                project.layout.buildDirectory.dir("generated/tenant/commonMain/kotlin"),
            )
        }

        project.tasks.register(
            "generateIosTenantXcconfig",
            GenerateIosXcconfigTask::class.java,
        ) {
            group = "tenant"
            description = "Generates iOS .xcconfig file from tenant.yaml"

            this.tenantFile.set(tenantConfigFile)
            environment.set(env)
            outputFile.set(
                project.rootProject.layout.projectDirectory.file(
                    extension.iosConfigOutputPath.get(),
                ),
            )
        }

        project.pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
            project.extensions.configure(
                KotlinMultiplatformExtension::class.java,
            ) {
                sourceSets.named("commonMain") {
                    kotlin.srcDir(
                        generateTenantConfigTask.flatMap { it.outputDir },
                    )
                }
            }
        }

        project.pluginManager.withPlugin("com.android.application") {
            configureAndroidApplication(
                project = project,
                tenantId = tenantId,
                env = env,
                tenantDirPath = tenantDir.asFile.absolutePath,
                tenantFilePath = tenantConfigFile.asFile.absolutePath,
            )
        }
    }

    private fun configureAndroidApplication(
        project: Project,
        tenantId: String,
        env: String,
        tenantDirPath: String,
        tenantFilePath: String,
    ) {
        val tenantFile = project.file(tenantFilePath)
        val tenantDir = project.file(tenantDirPath)
        val config = TenantConfigLoader.load(tenantFile)
        val apiBaseUrl = config.apiBaseUrl(env)

        val android = project.extensions.getByType(ApplicationExtension::class.java)

        android.buildFeatures.buildConfig = true

        android.defaultConfig.apply {
            applicationId = config.androidApp.applicationId

            resValue("string", "app_name", config.androidApp.appName)

            buildConfigField("String", "TENANT_ID", config.id.kotlinString())
            buildConfigField("String", "ENVIRONMENT", env.kotlinString())
            buildConfigField("String", "API_BASE_URL", apiBaseUrl.kotlinString())

            buildConfigField("boolean", "PAYMENTS_ENABLED", config.features.payments.toString())
            buildConfigField("boolean", "BONUSES_ENABLED", config.features.bonuses.toString())
            buildConfigField("boolean", "PICKUP_POINTS_ENABLED", config.features.pickupPoints.toString())
            buildConfigField("boolean", "RESTAURANT_MODE_ENABLED", config.features.restaurantMode.toString())

            manifestPlaceholders["tenantId"] = config.id
        }

        val androidResDir = tenantDir.resolve("android/res")
        if (androidResDir.exists()) {
            android.sourceSets
                .getByName("main")
                .res
                .directories
                .add(androidResDir.absolutePath)
        }

        val androidAssetsDir = tenantDir.resolve("android/assets")
        if (androidAssetsDir.exists()) {
            android.sourceSets
                .getByName("main")
                .assets
                .directories
                .add(androidAssetsDir.absolutePath)
        }

        configureGoogleServicesFileIfExists(
            project = project,
            tenantDir = tenantDir,
        )

        project.logger.lifecycle(
            "Configured Android tenant: $tenantId, env=$env, applicationId=${config.androidApp.applicationId}",
        )
    }

    private fun configureGoogleServicesFileIfExists(
        project: Project,
        tenantDir: java.io.File,
    ) {
        val googleServicesFile = tenantDir.resolve("android/google-services.json")

        if (!googleServicesFile.exists()) {
            return
        }

        val copyTask = project.tasks.register(
            "prepareTenantGoogleServices",
            Copy::class.java,
        ) {
            group = "tenant"
            description = "Copies tenant google-services.json into android app module"

            from(googleServicesFile)
            into(project.layout.projectDirectory)
        }

        project.tasks.configureEach {
            if (
                name == "preBuild" ||
                (name.startsWith("process") && name.endsWith("GoogleServices"))
            ) {
                dependsOn(copyTask)
            }
        }
    }
}