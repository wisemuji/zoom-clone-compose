import com.wisemuji.zoomclone.configureHiltAndroid
import com.wisemuji.zoomclone.configureKotlinAndroid
import com.wisemuji.zoomclone.configureSpotless

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureSpotless()
