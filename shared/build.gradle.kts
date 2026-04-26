import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    id("storeva.tenant")
}

tenantConfig {
    generatedPackage.set("ru.storeva.generated")
}

kotlin {
    android {
        namespace = "ru.storeva.android.shared"
        compileSdk = 36
        minSdk = 30

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
            export(libs.decompose)
            export(libs.essenty.lifecycle)
            export(libs.kotlinx.coroutines.core)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.decompose)
                implementation(libs.essenty.lifecycle)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.koin.core)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.websocket)
                implementation(libs.ktor.contentnegotiation)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.serialization.protobuf)
                implementation(libs.ktor.client.logging)
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
            dependencies {
                api(libs.decompose)
                api(libs.essenty.lifecycle)
                api(libs.kotlinx.coroutines.core)
                implementation(libs.ktor.client.darwin)
            }
        }
        iosX64Main.get().dependsOn(iosMain)
        iosArm64Main.get().dependsOn(iosMain)
        iosSimulatorArm64Main.get().dependsOn(iosMain)

        androidMain.dependencies {
            implementation(libs.androidx.security.crypto)
            implementation(libs.ktor.client.okhttp)
        }
    }
}