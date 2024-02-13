plugins {
    id("zoomclone.android.application")
    id("zoomclone.android.compose")
    id("kotlinx-serialization")
}

android {
    namespace = "com.wisemuji.zoomclone"

    defaultConfig {
        applicationId = "com.wisemuji.zoomclone"
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // stream
    implementation(libs.stream.video.ui.compose)
    implementation(libs.stream.video.ui.previewdata)

    // androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.compose.navigation)

    // retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}
