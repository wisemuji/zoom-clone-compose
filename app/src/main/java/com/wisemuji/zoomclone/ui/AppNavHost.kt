package com.wisemuji.zoomclone.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wisemuji.zoomclone.ui.joinmeeting.JoinMeetingScreen
import com.wisemuji.zoomclone.ui.lobby.LobbyScreen
import com.wisemuji.zoomclone.ui.meetingroom.navigation.meetingRoomScreen
import com.wisemuji.zoomclone.ui.newmeeting.NewMeetingScreen

enum class Screen {
    LOBBY,
    NEW_MEETING,
    JOIN_MEETING,
}

sealed class NavigationItem(val route: String) {
    data object Lobby : NavigationItem(Screen.LOBBY.name)
    data object NewMeeting : NavigationItem(Screen.NEW_MEETING.name)
    data object JoinMeeting : NavigationItem(Screen.JOIN_MEETING.name)
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = NavigationItem.Lobby.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Lobby.route) {
            LobbyScreen(navController)
        }
        composable(NavigationItem.NewMeeting.route) {
            NewMeetingScreen(navController)
        }
        composable(NavigationItem.JoinMeeting.route) {
            JoinMeetingScreen(navController)
        }
        meetingRoomScreen(onBackPressed = { navController.popBackStack() })
    }
}
