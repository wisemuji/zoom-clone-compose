package com.wisemuji.zoomclone.ui.joinmeeting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wisemuji.zoomclone.R
import com.wisemuji.zoomclone.model.MeetingOptions
import com.wisemuji.zoomclone.ui.component.DefaultHorizontalDivider
import com.wisemuji.zoomclone.ui.component.StatusBarColor
import com.wisemuji.zoomclone.ui.component.ZoomFullSizeButton
import com.wisemuji.zoomclone.ui.component.ZoomSwitchRow
import com.wisemuji.zoomclone.ui.component.ZoomTextField
import com.wisemuji.zoomclone.ui.theme.Gray60
import com.wisemuji.zoomclone.ui.theme.ZoomCloneComposeTheme

@Composable
fun JoinMeetingScreen(
    onBackPressed: () -> Unit,
    onJoinMeetingClick: (MeetingOptions) -> Unit,
) {
    var meetingId by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var audioOn by remember { mutableStateOf(true) }
    var videoOn by remember { mutableStateOf(true) }

    StatusBarColor(color = MaterialTheme.colorScheme.surface, isIconLight = true)
    Scaffold(
        topBar = { JoinMeetingTopAppBar(onBack = onBackPressed) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            DefaultHorizontalDivider()
            Spacer(modifier = Modifier.padding(12.dp))
            DefaultHorizontalDivider()
            ZoomTextField(
                value = meetingId,
                onValueChange = { meetingId = it },
                placeholderText = stringResource(R.string.meeting_id_placeholder),
            )
            ZoomTextField(
                value = name,
                onValueChange = { name = it },
                placeholderText = stringResource(R.string.your_name_placeholder),
            )
            ZoomFullSizeButton(
                text = stringResource(R.string.join),
                onClick = { onJoinMeetingClick(MeetingOptions(meetingId, name, audioOn, videoOn)) },
                modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp),
            )
            Text(
                text = stringResource(R.string.join_notes),
                fontSize = 12.sp,
                color = Gray60,
                lineHeight = 14.sp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 42.dp)
            )
            JoinOptions(
                doNotConnectAudio = !audioOn,
                onCheckedDoNotConnectAudio = { audioOn = !it },
                turnOffVideo = !videoOn,
                onCheckedTurnOffVideo = { videoOn = !it }
            )
        }
    }
}

@Composable
private fun JoinMeetingTopAppBar(onBack: () -> Unit) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 4.dp)
    ) {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(52.dp),
            )
        }
        Text(
            text = stringResource(R.string.join_meeting_title),
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun JoinOptions(
    doNotConnectAudio: Boolean = false,
    onCheckedDoNotConnectAudio: (Boolean) -> Unit = {},
    turnOffVideo: Boolean = false,
    onCheckedTurnOffVideo: (Boolean) -> Unit = {},
) {
    Column {
        Text(
            text = stringResource(R.string.join_options),
            fontSize = 12.sp,
            color = Gray60,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 16.dp)
        )
        DefaultHorizontalDivider()
        ZoomSwitchRow(
            title = stringResource(R.string.do_not_connect_audio),
            checked = doNotConnectAudio,
            onCheckedChange = onCheckedDoNotConnectAudio
        )
        DefaultHorizontalDivider()
        ZoomSwitchRow(
            title = stringResource(R.string.turn_off_video),
            checked = turnOffVideo,
            onCheckedChange = onCheckedTurnOffVideo
        )
        DefaultHorizontalDivider()
    }
}

@Preview
@Composable
private fun JoinMeetingScreenPreview() {
    ZoomCloneComposeTheme {
        JoinMeetingScreen({}, {})
    }
}
