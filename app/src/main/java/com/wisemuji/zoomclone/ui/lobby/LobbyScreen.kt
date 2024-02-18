package com.wisemuji.zoomclone.ui.lobby

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.wisemuji.zoomclone.ui.Screen
import com.wisemuji.zoomclone.ui.theme.ZoomCloneComposeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LobbyScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Meetings") },
                actions = {
                    IconButton(onClick = { /*TODO: Not Implemented*/ }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Information"
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Button(onClick = { navController.navigate(Screen.NEW_MEETING.name) }) {
                Text(text = "New Meeting")
            }
            Button(onClick = { navController.navigate(Screen.JOIN_MEETING.name) }) {
                Text(text = "Join Meeting")
            }
        }
    }
}

@Preview
@Composable
private fun LobbyScreenPreview() {
    ZoomCloneComposeTheme {
        LobbyScreen(navController = rememberNavController())
    }
}
