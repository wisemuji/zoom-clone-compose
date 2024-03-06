package com.wisemuji.zoomclone.ui.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape
import io.getstream.video.android.compose.theme.StreamColors
import io.getstream.video.android.compose.theme.StreamShapes
import io.getstream.video.android.compose.theme.VideoTheme

@Composable
fun ZoomVideoTheme(
    content: @Composable () -> Unit,
) {
    VideoTheme(
        colors = StreamColors
            .defaultColors()
            .copy(
                appBackground = MaterialTheme.colorScheme.inverseSurface,
                barsBackground = MaterialTheme.colorScheme.inverseSurface,
                inputBackground = MaterialTheme.colorScheme.inverseSurface,
                textHighEmphasis = MaterialTheme.colorScheme.inverseOnSurface,
            ),
        shapes = StreamShapes
            .defaultShapes()
            .copy(
                participantContainerShape = RectangleShape,
                callControls = RectangleShape
            ),
        content = content,
    )
}
