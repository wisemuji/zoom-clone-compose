plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidHilt") {
            id = "zoomclone.android.hilt"
            implementationClass = "com.wisemuji.zoomclone.HiltAndroidPlugin"
        }
    }
}
