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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.wisemuji.zoomclone.R
import com.wisemuji.zoomclone.ui.component.DefaultHorizontalDivider
import com.wisemuji.zoomclone.ui.component.StatusBarColor
import com.wisemuji.zoomclone.ui.theme.Gray50
import com.wisemuji.zoomclone.ui.theme.ZoomCloneComposeTheme

@Composable
fun LobbyScreen(
    onNewMeetingClick: () -> Unit,
    onJoinMeetingClick: () -> Unit,
) {
    var showInfoDialog by remember { mutableStateOf(false) }

    if (showInfoDialog) {
        LobbyInfoDialog(onDismissRequest = { showInfoDialog = false })
    }
    StatusBarColor(color = MaterialTheme.colorScheme.surfaceContainerHighest, isIconLight = false)
    Scaffold(
        topBar = { LobbyScreenTopAppBar { showInfoDialog = true } }
    ) { innerPadding ->
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                LobbyItem(
                    icon = painterResource(id = R.drawable.ic_zoom_video_on),
                    caption = stringResource(R.string.new_meeting_navigator),
                    color = MaterialTheme.colorScheme.secondary,
                ) { onNewMeetingClick() }
                LobbyItem(
                    icon = painterResource(id = R.drawable.ic_zoom_join_meeting),
                    caption = stringResource(R.string.join_meeting_navigator),
                    color = MaterialTheme.colorScheme.primary,
                ) { onJoinMeetingClick() }
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
            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 4.dp)
    ) {
        Text(
            text = stringResource(R.string.lobby_title),
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.inverseOnSurface
        )
        IconButton(onClick = onInfoClick) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "information",
                tint = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
    }
}

@Composable
private fun LobbyItem(
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
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(
            text = caption,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            letterSpacing = (-0.45).sp,
        )
    }
}

@Composable
private fun LobbyInfoDialog(
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(text = stringResource(id = R.string.app_information_title))
        },
        text = {
            Text(text = stringResource(id = R.string.app_information_content))
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text(text = stringResource(id = R.string.dialog_confirm))
            }
        }
    )
}

@Preview
@Composable
private fun LobbyScreenPreview() {
    ZoomCloneComposeTheme {
        LobbyScreen({}, {})
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
            color = MaterialTheme.colorScheme.secondary,
        ) {}
    }
}
