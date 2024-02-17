package com.wisemuji.zoomclone.ui.meetingroom

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MeetingRoomScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        Text(
            text = "MeetingRoomScreen",
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
private fun MeetingRoomScreenPreview() {
    MeetingRoomScreen(navController = rememberNavController())
}
