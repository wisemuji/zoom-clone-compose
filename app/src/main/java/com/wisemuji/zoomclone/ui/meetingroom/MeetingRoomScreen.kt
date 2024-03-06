package com.wisemuji.zoomclone.ui.meetingroom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wisemuji.zoomclone.R
import com.wisemuji.zoomclone.ui.component.StatusBarColor
import com.wisemuji.zoomclone.ui.component.ZoomVideoTheme
import com.wisemuji.zoomclone.ui.theme.Black
import com.wisemuji.zoomclone.ui.theme.White
import io.getstream.video.android.compose.ui.components.call.CallAppBar
import io.getstream.video.android.compose.ui.components.call.controls.ControlActions
import io.getstream.video.android.compose.ui.components.call.controls.actions.DefaultOnCallActionHandler
import io.getstream.video.android.compose.ui.components.call.controls.actions.FlipCameraAction
import io.getstream.video.android.compose.ui.components.call.controls.actions.LeaveCallAction
import io.getstream.video.android.compose.ui.components.call.renderer.ParticipantVideo
import io.getstream.video.android.compose.ui.components.call.renderer.ParticipantsLayout
import io.getstream.video.android.compose.ui.components.call.renderer.RegularVideoRendererStyle
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.call.state.LeaveCall
import io.getstream.video.android.core.mapper.ReactionMapper
import io.getstream.video.android.mock.StreamPreviewDataUtils
import io.getstream.video.android.mock.previewCall

@Composable
fun MeetingRoomScreen(
    onBackPressed: () -> Unit,
    viewModel: MeetingRoomViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    StatusBarColor(color = Black, isIconLight = false)
    LaunchedEffect(key1 = Unit) {
        viewModel.loadMeeting()
    }
    when (uiState) {
        is MeetingUiState.Success -> {
            val call = (uiState as MeetingUiState.Success).call
            MeetingRoomContent(call = call, onBackPressed)
        }

        MeetingUiState.Loading -> {
            MeetingRoomPlaceholder(text = stringResource(R.string.connecting))
        }

        MeetingUiState.Error -> {
            MeetingRoomPlaceholder(text = stringResource(R.string.error))
        }
    }
}

@Composable
fun MeetingRoomContent(
    call: Call,
    onLeaveCall: () -> Unit,
) {
    var isShowingReactionDialog by remember { mutableStateOf(false) }
    DisposableEffect(key1 = call.id) {
        onDispose { call.leave() }
    }
    ZoomVideoTheme {
        Scaffold(
            topBar = {
                MeetingRoomTopAppBar(
                    call = call,
                    onLeaveCall = onLeaveCall,
                )
            },
            bottomBar = {
                MeetingRoomBottomBar(
                    call = call,
                    onLeaveCall = onLeaveCall,
                    toggleReactions = { isShowingReactionDialog = true },
                )
            },
        ) {
            ParticipantsLayout(
                call = call,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                style = RegularVideoRendererStyle(reactionPosition = Alignment.Center),
                videoRenderer = { videoModifier, videoCall, videoParticipant, videoStyle ->
                    ParticipantVideo(
                        modifier = videoModifier,
                        call = videoCall,
                        participant = videoParticipant,
                        style = videoStyle,
                        connectionIndicatorContent = {},
                    )
                },
            )
            if (isShowingReactionDialog) {
                ReactionsMenu(
                    call = call,
                    reactionMapper = ReactionMapper.defaultReactionMapper(),
                    onDismiss = { isShowingReactionDialog = false }
                )
            }
        }
    }
}

@Composable
private fun MeetingRoomTopAppBar(
    call: Call,
    onLeaveCall: () -> Unit,
) {
    CallAppBar(
        call = call,
        leadingContent = {
            IconButton(onClick = { onLeaveCall() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Back",
                    tint = White,
                    modifier = Modifier.size(52.dp),
                )
            }
            FlipCameraAction(
                onCallAction = { call.camera.flip() }
            )
        },
        title = stringResource(R.string.meeting_room_title),
        trailingContent = {
            LeaveCallAction(
                modifier = Modifier.size(52.dp),
                onCallAction = { onLeaveCall() }
            )
        },
        modifier = Modifier
            .background(Black)
    )
}

@Composable
private fun MeetingRoomBottomBar(
    call: Call,
    onLeaveCall: () -> Unit,
    toggleReactions: () -> Unit,
) {
    ControlActions(
        modifier = Modifier.navigationBarsPadding(),
        call = call,
        onCallAction = { action ->
            when (action) {
                is LeaveCall -> {
                    onLeaveCall()
                }

                else -> DefaultOnCallActionHandler.onCallAction(call, action)
            }
        },
        actions = listOf(
            { ToggleMicrophoneButton(call) },
            { ToggleCameraButton(call) },
            { ToggleReactionsButton(toggleReactions) },
        ),
    )
}

@Composable
private fun MeetingRoomPlaceholder(
    text: String,
) {
    Scaffold(
        containerColor = Black
    ) { innerPadding ->
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = White,
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun MeetingRoomContentPreview() {
    StreamPreviewDataUtils.initializeStreamVideo(LocalContext.current)
    MeetingRoomContent(previewCall) {}
}

@Preview
@Composable
private fun MeetingRoomError() {
    MeetingRoomPlaceholder(text = stringResource(R.string.error))
}
