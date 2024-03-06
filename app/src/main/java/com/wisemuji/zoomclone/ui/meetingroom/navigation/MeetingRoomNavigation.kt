/*
 * Copyright 2024 Suhyeon(wisemuji) and Stream.IO, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
