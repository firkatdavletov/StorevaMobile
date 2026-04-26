plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly("com.android.tools.build:gradle:${libs.versions.agp.get()}")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")

    implementation("org.yaml:snakeyaml:2.2")
}

gradlePlugin {
    plugins {
        register("tenantPlugin") {
            id = "storeva.tenant"
            implementationClass = "ru.storeva.buildlogic.tenant.TenantPlugin"
        }
    }
}