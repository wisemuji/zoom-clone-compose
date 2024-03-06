package com.wisemuji.zoomclone.model

import kotlinx.serialization.Serializable

@Serializable
data class MeetingOptions(
    val meetingId: String = "",
    val username: String = "",
    val audioOn: Boolean = true,
    val videoOn: Boolean = true,
)
