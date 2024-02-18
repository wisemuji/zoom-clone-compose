package com.wisemuji.zoomclone.ui.lobby

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wisemuji.zoomclone.R
import com.wisemuji.zoomclone.ui.Screen
import com.wisemuji.zoomclone.ui.component.DefaultHorizontalDivider
import com.wisemuji.zoomclone.ui.component.StatusBarColor
import com.wisemuji.zoomclone.ui.theme.Blue
import com.wisemuji.zoomclone.ui.theme.Gray10
import com.wisemuji.zoomclone.ui.theme.Gray50
import com.wisemuji.zoomclone.ui.theme.Gray80
import com.wisemuji.zoomclone.ui.theme.Orange
import com.wisemuji.zoomclone.ui.theme.White
import com.wisemuji.zoomclone.ui.theme.ZoomCloneComposeTheme

@Composable
fun LobbyScreen(navController: NavHostController) {
    StatusBarColor(color = Gray80, isIconLight = false)
    Scaffold(
        topBar = {
            LobbyScreenTopAppBar { /* TODO: not implemented yet */ }
        }
    ) { innerPadding ->
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .padding(innerPadding)
                    .background(Gray10)
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                LobbyItem(
                    icon = painterResource(id = R.drawable.ic_zoom_video_on),
                    caption = stringResource(R.string.new_meeting_navigator),
                    color = Orange
                ) { navController.navigate(Screen.NEW_MEETING.name) }
                LobbyItem(
                    icon = painterResource(id = R.drawable.ic_zoom_join_meeting),
                    caption = stringResource(R.string.join_meeting_navigator),
                    color = Blue
                ) { navController.navigate(Screen.JOIN_MEETING.name) }
                LobbyItem(
                    icon = painterResource(id = R.drawable.ic_zoom_breakout),
                    caption = stringResource(R.string.working_in_progress),
                    color = Gray50
                )
                LobbyItem(
                    icon = painterResource(id = R.drawable.ic_zoom_breakout),
                    caption = stringResource(R.string.working_in_progress),
                    color = Gray50
                )
            }
            DefaultHorizontalDivider()
        }
    }
}

@Composable
private fun LobbyScreenTopAppBar(
    onInfoClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.CenterEnd,
        modifier = Modifier
            .background(Gray80)
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 4.dp)
    ) {
        Text(
            text = stringResource(R.string.lobby_title),
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.Center),
            color = White
        )
        IconButton(onClick = onInfoClick) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "information",
                tint = White
            )
        }
    }
}

@Composable
fun LobbyItem(
    icon: Painter,
    caption: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(color)
                .size(64.dp)
                .clickable { onClick() },
        ) {
            Icon(
                painter = icon,
                contentDescription = caption,
                tint = White
            )
        }
        Text(
            text = caption,
            fontSize = 12.sp,
            color = Gray80,
            letterSpacing = (-0.45).sp,
        )
    }
}

@Preview
@Composable
private fun LobbyScreenPreview() {
    ZoomCloneComposeTheme {
        LobbyScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
private fun LobbyScreenTopAppBarPreview() {
    ZoomCloneComposeTheme {
        LobbyScreenTopAppBar() {}
    }
}

@Preview(showBackground = true)
@Composable
private fun LobbyItemPreview() {
    ZoomCloneComposeTheme {
        LobbyItem(
            icon = painterResource(id = R.drawable.ic_zoom_video_on),
            caption = "New Meeting",
            color = Orange
        ) { }
    }
}
