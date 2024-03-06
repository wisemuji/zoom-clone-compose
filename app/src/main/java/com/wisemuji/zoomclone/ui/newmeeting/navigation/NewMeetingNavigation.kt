package com.wisemuji.zoomclone.ui.newmeeting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wisemuji.zoomclone.model.MeetingOptions
import com.wisemuji.zoomclone.ui.newmeeting.NewMeetingScreen
import com.wisemuji.zoomclone.ui.newmeeting.navigation.NewMeetingRoute.ROUTE

fun NavController.navigateToNewMeeting() {
    navigate(ROUTE)
}

fun NavGraphBuilder.newMeetingScreen(
    onBackPressed: () -> Unit,
    onJoinMeetingClick: (MeetingOptions) -> Unit,
) {
    composable(route = ROUTE) {
        NewMeetingScreen(onBackPressed, onJoinMeetingClick)
    }
}

object NewMeetingRoute {
    const val ROUTE = "new_meeting"
}
