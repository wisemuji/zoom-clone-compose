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

package com.wisemuji.zoomclone.ui.meetingroom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.wisemuji.zoomclone.ui.component.ZoomVideoTheme
import com.wisemuji.zoomclone.ui.theme.ZoomCloneComposeTheme
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.mapper.ReactionMapper
import io.getstream.video.android.mock.StreamPreviewDataUtils
import io.getstream.video.android.mock.previewCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

private data class ReactionItemData(val emojiDescription: String, val emojiCode: String)

private object DefaultReactionsMenuData {
    val mainReaction = ReactionItemData("Raise hand", ":raise-hand:")
    val defaultReactions = listOf(
        ReactionItemData("Wave", ":hello:"),
        ReactionItemData("Like", ":raise-hand:"),
        ReactionItemData("Heart", ":heart:"),
        ReactionItemData("Joy", "😂"),
        ReactionItemData("Opened mouth", "😮"),
        ReactionItemData("Fireworks", ":fireworks:"),
    )
}

@Composable
internal fun ReactionsMenu(
    call: Call,
    reactionMapper: ReactionMapper,
    onDismiss: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val modifier = Modifier
        .wrapContentWidth()
    val onEmojiSelected: (emoji: String) -> Unit = {
        sendReaction(scope, call, it, onDismiss)
    }

    Dialog(onDismiss) {
        Card(
            colors = CardDefaults.cardColors().copy(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                contentColor = MaterialTheme.colorScheme.inverseOnSurface,
            ),
            modifier = modifier.wrapContentWidth(),
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(horizontalArrangement = Arrangement.Center) {
                    ReactionItem(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textModifier = Modifier.fillMaxWidth(),
                        reactionMapper = reactionMapper,
                        reaction = DefaultReactionsMenuData.mainReaction,
                        showDescription = true,
                        onEmojiSelected = onEmojiSelected,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                ) {
                    DefaultReactionsMenuData.defaultReactions.forEach {
                        ReactionItem(
                            modifier = modifier.weight(1f),
                            reactionMapper = reactionMapper,
                            onEmojiSelected = onEmojiSelected,
                            reaction = it,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ReactionItem(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    reactionMapper: ReactionMapper,
    reaction: ReactionItemData,
    onEmojiSelected: (emoji: String) -> Unit,
    showDescription: Boolean = false,
) {
    val mappedEmoji = reactionMapper.map(reaction.emojiCode)
    Box(
        modifier = modifier
            .clickable {
                onEmojiSelected(reaction.emojiCode)
            }
            .padding(2.dp),
    ) {
        Text(
            textAlign = TextAlign.Center,
            modifier = textModifier.padding(12.dp),
            text = "$mappedEmoji${if (showDescription) reaction.emojiDescription else ""}",
        )
    }
}

private fun sendReaction(scope: CoroutineScope, call: Call, emoji: String, onDismiss: () -> Unit) {
    scope.launch {
        call.sendReaction("default", emoji)
        onDismiss()
    }
}

@Preview
@Composable
private fun ReactionMenuPreview() {
    ZoomCloneComposeTheme {
        ZoomVideoTheme {
            StreamPreviewDataUtils.initializeStreamVideo(LocalContext.current)
            ReactionsMenu(
                call = previewCall,
                reactionMapper = ReactionMapper.defaultReactionMapper(),
                onDismiss = { },
            )
        }
    }
}
