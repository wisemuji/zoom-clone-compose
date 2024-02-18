package com.wisemuji.zoomclone.ui.meetingroom.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wisemuji.zoomclone.ui.meetingroom.MeetingRoomScreen
import com.wisemuji.zoomclone.ui.meetingroom.navigation.MeetingRoomRoute.ARGS_CALL_ID
import com.wisemuji.zoomclone.ui.meetingroom.navigation.MeetingRoomRoute.ROUTE
import java.net.URLDecoder
import java.net.URLEncoder

private val URL_CHARACTER_ENCODING = Charsets.UTF_8.name()

class MeetingRoomArgs(val callId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
        this(
            URLDecoder.decode(
                checkNotNull(savedStateHandle[ARGS_CALL_ID]),
                URL_CHARACTER_ENCODING
            )
        )
}

fun NavController.navigateToMeetingRoom(meetingId: String) {
    val encodedId = URLEncoder.encode(meetingId, URL_CHARACTER_ENCODING)
    navigate(ROUTE.replace("{$ARGS_CALL_ID}", encodedId)) {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.meetingRoomScreen(
    onBackPressed: () -> Unit,
) {
    composable(
        route = ROUTE,
        arguments = listOf(
            navArgument(ARGS_CALL_ID) { type = NavType.StringType },
        ),
    ) {
        MeetingRoomScreen(onBackPressed)
    }
}

object MeetingRoomRoute {
    const val ARGS_CALL_ID = "call_id"
    const val ROUTE = "meeting_room/{${ARGS_CALL_ID}}"
}
