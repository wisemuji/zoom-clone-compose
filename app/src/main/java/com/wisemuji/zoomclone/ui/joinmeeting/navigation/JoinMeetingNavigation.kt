package com.wisemuji.zoomclone.ui.joinmeeting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wisemuji.zoomclone.model.MeetingOptions
import com.wisemuji.zoomclone.ui.joinmeeting.JoinMeetingScreen
import com.wisemuji.zoomclone.ui.joinmeeting.navigation.JoinMeetingRoute.ROUTE

fun NavController.navigateToJoinMeeting() {
    navigate(ROUTE)
}

fun NavGraphBuilder.joinMeetingScreen(
    onBackPressed: () -> Unit,
    onJoinMeetingClick: (MeetingOptions) -> Unit,
) {
    composable(route = ROUTE) {
        JoinMeetingScreen(onBackPressed, onJoinMeetingClick)
    }
}

object JoinMeetingRoute {
    const val ROUTE = "join_meeting"
}
