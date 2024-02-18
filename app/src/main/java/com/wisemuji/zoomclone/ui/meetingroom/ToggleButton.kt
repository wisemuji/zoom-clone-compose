package com.wisemuji.zoomclone.ui.meetingroom

import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wisemuji.zoomclone.R
import com.wisemuji.zoomclone.ui.theme.Black
import io.getstream.video.android.core.Call

@Composable
fun ToggleMicrophoneButton(call: Call) {
    val isMicrophoneEnabled by call.microphone.isEnabled.collectAsStateWithLifecycle()
    ToggleButton(
        onClick = { call.microphone.setEnabled(!isMicrophoneEnabled) },
        enabled = isMicrophoneEnabled,
        enabledIcon = R.drawable.ic_zoom_mic_on,
        disabledIcon = R.drawable.ic_zoom_mic_off,
    )
}

@Composable
fun ToggleCameraButton(call: Call) {
    val isCameraEnabled by call.camera.isEnabled.collectAsStateWithLifecycle()
    ToggleButton(
        onClick = { call.camera.setEnabled(!isCameraEnabled) },
        enabled = isCameraEnabled,
        enabledIcon = R.drawable.ic_zoom_video_on,
        disabledIcon = R.drawable.ic_zoom_video_off,
    )
}

@Composable
fun ToggleReactionsButton(toggleReactions: () -> Unit) {
    ToggleButton(
        onClick = toggleReactions,
        enabled = true,
        enabledIcon = R.drawable.ic_zoom_reaction,
    )
}

@Composable
private fun ToggleButton(
    onClick: () -> Unit,
    enabled: Boolean,
    enabledIcon: Int,
    disabledIcon: Int = enabledIcon,
) {
    FilledIconButton(
        onClick = onClick,
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = Black,
        ),
    ) {
        if (enabled) {
            Icon(
                painterResource(enabledIcon),
                tint = Color.White,
                contentDescription = "enable toggle"
            )
        } else {
            Icon(
                painterResource(disabledIcon),
                tint = Color.White,
                contentDescription = "disable toggle"
            )
        }
    }
}
