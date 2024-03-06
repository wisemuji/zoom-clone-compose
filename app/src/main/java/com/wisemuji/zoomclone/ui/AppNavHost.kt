package com.wisemuji.zoomclone.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.wisemuji.zoomclone.ui.joinmeeting.navigation.joinMeetingScreen
import com.wisemuji.zoomclone.ui.joinmeeting.navigation.navigateToJoinMeeting
import com.wisemuji.zoomclone.ui.lobby.navigation.LobbyRoute
import com.wisemuji.zoomclone.ui.lobby.navigation.lobbyScreen
import com.wisemuji.zoomclone.ui.meetingroom.navigation.meetingRoomScreen
import com.wisemuji.zoomclone.ui.meetingroom.navigation.navigateToMeetingRoom
import com.wisemuji.zoomclone.ui.newmeeting.navigation.navigateToNewMeeting
import com.wisemuji.zoomclone.ui.newmeeting.navigation.newMeetingScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = LobbyRoute.ROUTE,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        lobbyScreen(
            onNewMeetingClick = navController::navigateToNewMeeting,
            onJoinMeetingClick = navController::navigateToJoinMeeting,
        )
        newMeetingScreen(
            onBackPressed = navController::popBackStack,
            onJoinMeetingClick = navController::navigateToMeetingRoom
        )
        joinMeetingScreen(
            onBackPressed = navController::popBackStack,
            onJoinMeetingClick = navController::navigateToMeetingRoom
        )
        meetingRoomScreen(onBackPressed = navController::popBackStack)
    }
}
