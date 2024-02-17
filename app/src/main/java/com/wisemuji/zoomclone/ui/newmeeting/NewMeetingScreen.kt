package com.wisemuji.zoomclone.ui.newmeeting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wisemuji.zoomclone.R
import com.wisemuji.zoomclone.ui.component.BasicSwitchRow
import com.wisemuji.zoomclone.ui.component.DefaultHorizontalDivider
import com.wisemuji.zoomclone.ui.component.FullSizeButton
import com.wisemuji.zoomclone.ui.theme.Gray10
import com.wisemuji.zoomclone.ui.theme.ZoomCloneComposeTheme

@Composable
fun NewMeetingScreen(navController: NavHostController) {
    var videoOn by remember { mutableStateOf(true) }
    var usePersonalMeetingId by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { NewMeetingTopAppBar(onBack = { navController.popBackStack() }) }
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
            BasicSwitchRow(
                title = stringResource(R.string.video_on),
                checked = videoOn,
                onCheckedChange = { videoOn = it }
            )
            DefaultHorizontalDivider()
            BasicSwitchRow(
                title = stringResource(R.string.use_personal_meeting_id),
                subtitle = stringResource(R.string.personal_meeting_id),
                checked = usePersonalMeetingId,
                onCheckedChange = { usePersonalMeetingId = it }
            )
            DefaultHorizontalDivider()
            FullSizeButton(
                text = stringResource(R.string.start_a_meeting),
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(24.dp),
            )
        }
    }
}

@Composable
private fun NewMeetingTopAppBar(onBack: () -> Unit) {
    Box(
        contentAlignment = CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 4.dp)
    ) {
        TextButton(onClick = onBack) {
            Text(text = stringResource(R.string.cancel), fontSize = 17.sp)
        }
        Text(
            text = stringResource(R.string.start_a_meeting),
            fontSize = 17.sp,
            fontWeight = Medium,
            modifier = Modifier.align(Center)
        )
    }
}

@Preview
@Composable
fun NewMeetingScreenPreview() {
    ZoomCloneComposeTheme {
        NewMeetingScreen(navController = rememberNavController())
    }
}