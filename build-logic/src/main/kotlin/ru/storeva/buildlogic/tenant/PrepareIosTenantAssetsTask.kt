package ru.storeva.buildlogic.tenant

import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

abstract class PrepareIosTenantAssetsTask : DefaultTask() {

    @get:InputDirectory
    @get:Optional
    abstract val inputAssetsDir: DirectoryProperty

    @get:OutputDirectory
    abstract val outputAssetsDir: DirectoryProperty

    @TaskAction
    fun prepare() {
        val inputDir = inputAssetsDir.asFile.orNull
        val outputDir = outputAssetsDir.get().asFile

        if (inputDir == null || !inputDir.exists()) {
            logger.lifecycle("No iOS tenant assets found, skip")
            return
        }

        outputDir.mkdirs()

        inputDir
            .listFiles()
            ?.filter { file ->
                file.isDirectory && (
                    file.name.endsWith(".imageset") ||
                        file.name.endsWith(".appiconset") ||
                        file.name.endsWith(".colorset") ||
                        file.name.endsWith(".dataset") ||
                        file.name.endsWith(".symbolset")
                )
            }?.forEach { assetSet ->
                val target = outputDir.resolve(assetSet.name)

                if (target.exists()) {
                    target.deleteRecursively()
                }

                assetSet.copyRecursively(
                    target = target,
                    overwrite = true,
                )

                logger.lifecycle("Copied iOS tenant asset: ${assetSet.name}")
            }

        // Ensure root Contents.json exists
        val contentsJson = outputDir.resolve("Contents.json")
        if (!contentsJson.exists()) {
            contentsJson.writeText(
                """
                {
                  "info": {
                    "author": "xcode",
                    "version": 1
                  }
                }
                """.trimIndent(),
            )
        }
    }
}