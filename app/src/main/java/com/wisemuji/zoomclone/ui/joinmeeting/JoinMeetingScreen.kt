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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wisemuji.zoomclone.R
import com.wisemuji.zoomclone.ui.component.BasicSwitchRow
import com.wisemuji.zoomclone.ui.component.DefaultHorizontalDivider
import com.wisemuji.zoomclone.ui.component.DefaultTextField
import com.wisemuji.zoomclone.ui.component.FullSizeButton
import com.wisemuji.zoomclone.ui.component.StatusBarColor
import com.wisemuji.zoomclone.ui.meetingroom.navigation.navigateToMeetingRoom
import com.wisemuji.zoomclone.ui.theme.Blue
import com.wisemuji.zoomclone.ui.theme.Gray10
import com.wisemuji.zoomclone.ui.theme.Gray60
import com.wisemuji.zoomclone.ui.theme.White
import com.wisemuji.zoomclone.ui.theme.ZoomCloneComposeTheme

@Composable
fun JoinMeetingScreen(navController: NavHostController) {
    var meetingId by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    StatusBarColor(color = White, isIconLight = true)
    Scaffold(
        topBar = { JoinMeetingTopAppBar(onBack = { navController.popBackStack() }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Gray10)
        ) {
            DefaultHorizontalDivider()
            Spacer(modifier = Modifier.padding(12.dp))
            DefaultHorizontalDivider()
            DefaultTextField(
                value = meetingId,
                onValueChange = { meetingId = it },
                placeholderText = stringResource(R.string.meeting_id_placeholder),
            )
            DefaultTextField(
                value = name,
                onValueChange = { name = it },
                placeholderText = stringResource(R.string.your_name_placeholder),
            )
            FullSizeButton(
                text = stringResource(R.string.join),
                onClick = {
                    navController.navigateToMeetingRoom(meetingId)
                },
                modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp),
            )
            Text(
                text = stringResource(R.string.join_notes),
                fontSize = 12.sp,
                color = Gray60,
                lineHeight = 14.sp,
                modifier = Modifier.padding(start =16.dp, end = 16.dp, top = 10.dp, bottom = 42.dp)
            )
            JoinOptions()
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
                tint = Blue,
                modifier = Modifier.size(52.dp),
            )
        }
        Text(
            text = stringResource(R.string.join_a_meeting),
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
        BasicSwitchRow(
            title = stringResource(R.string.do_not_connect_audio),
            checked = doNotConnectAudio,
            onCheckedChange = onCheckedDoNotConnectAudio
        )
        DefaultHorizontalDivider()
        BasicSwitchRow(
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
        JoinMeetingScreen(navController = rememberNavController())
    }
}
