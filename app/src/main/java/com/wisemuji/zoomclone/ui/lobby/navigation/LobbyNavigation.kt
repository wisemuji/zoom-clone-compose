package com.wisemuji.zoomclone.ui.lobby.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wisemuji.zoomclone.ui.lobby.LobbyScreen
import com.wisemuji.zoomclone.ui.lobby.navigation.LobbyRoute.ROUTE

fun NavGraphBuilder.lobbyScreen(
    onNewMeetingClick: () -> Unit,
    onJoinMeetingClick: () -> Unit,
) {
    composable(route = ROUTE) {
        LobbyScreen(
            onNewMeetingClick = onNewMeetingClick,
            onJoinMeetingClick = onJoinMeetingClick,
        )
    }
}

object LobbyRoute {
    const val ROUTE = "lobby"
}
