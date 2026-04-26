package ru.storeva.buildlogic.tenant

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

abstract class TenantExtension @Inject constructor(
    objects: ObjectFactory,
) {
    val generatedPackage: Property<String> =
        objects.property(String::class.java).convention("ru.storeva.generated")

    val iosConfigOutputPath: Property<String> =
        objects.property(String::class.java).convention("iosApp/Configs/Tenant.generated.xcconfig")
}