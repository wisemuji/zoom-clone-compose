package com.wisemuji.zoomclone.ui.meetingroom.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wisemuji.zoomclone.model.MeetingOptions
import com.wisemuji.zoomclone.ui.meetingroom.MeetingRoomScreen
import com.wisemuji.zoomclone.ui.meetingroom.navigation.MeetingRoomRoute.ARGS_MEETING_OPTIONS
import com.wisemuji.zoomclone.ui.meetingroom.navigation.MeetingRoomRoute.ROUTE
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun NavController.navigateToMeetingRoom(meetingOptions: MeetingOptions) {
    val encoded = Json.encodeToString(meetingOptions)
    popBackStack()
    navigate(ROUTE.replace("{$ARGS_MEETING_OPTIONS}", encoded)) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.meetingRoomScreen(
    onBackPressed: () -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(ARGS_MEETING_OPTIONS) { type = NavType.StringType },
        ),
    ) {
        MeetingRoomScreen(onBackPressed)
    }
}

object MeetingRoomRoute {
    const val ARGS_MEETING_OPTIONS = "meeting_options"
    const val ROUTE = "meeting_room/{${ARGS_MEETING_OPTIONS}}"
}
