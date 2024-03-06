package com.wisemuji.zoomclone

import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureSpotless() {
    pluginManager.apply("com.diffplug.spotless")

    extensions.configure<SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")
            ktlint("1.2.1")
                .editorConfigOverride(
                    mapOf(
                        "ktlint_standard_max-line-length" to "disabled",
                        "ktlint_function_naming_ignore_when_annotated_with" to "Composable"
                    )
                )

            trimTrailingWhitespace()
            indentWithSpaces()
            endWithNewline()

            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
        }
        kotlinGradle {
            target("**/*.kts")
            targetExclude("**/build/**/*.kts")
            ktlint("1.2.1")

            trimTrailingWhitespace()
            indentWithSpaces()
            endWithNewline()

            licenseHeaderFile(
                rootProject.file("spotless/copyright.kts"),
                "(^(?![\\/ ]\\*).*$)"
            )
        }
    }
}
