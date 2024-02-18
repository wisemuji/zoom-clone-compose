package com.wisemuji.zoomclone

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.getstream.video.android.core.StreamVideo
import io.getstream.video.android.core.StreamVideoBuilder
import io.getstream.video.android.model.User

@HiltAndroidApp
class ZoomCloneComposeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initStreamVideo()
    }

    private fun initStreamVideo() {
        val userId = "wisemuji"
        StreamVideoBuilder(
            context = applicationContext,
            apiKey = BuildConfig.STREAM_API_KEY,
            token = StreamVideo.devToken(userId),
            user = User(
                id = userId,
                name = "Wisemuji",
                image = "http://placekitten.com/200/300",
                role = "admin"
            )
        ).build()
    }
}
