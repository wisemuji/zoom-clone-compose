/*
 * Copyright 2024 Suhyeon(wisemuji) and Stream.IO, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
                callControls = RectangleShape,
            ),
        content = content,
    )
}
