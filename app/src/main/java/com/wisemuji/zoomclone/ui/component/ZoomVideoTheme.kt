package com.wisemuji.zoomclone.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape
import com.wisemuji.zoomclone.ui.theme.Black
import com.wisemuji.zoomclone.ui.theme.White
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
                appBackground = Black,
                barsBackground = Black,
                inputBackground = Black,
                textHighEmphasis = White,
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
