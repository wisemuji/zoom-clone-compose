import com.wisemuji.zoomclone.configureHiltAndroid
import com.wisemuji.zoomclone.configureKotlinAndroid

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
