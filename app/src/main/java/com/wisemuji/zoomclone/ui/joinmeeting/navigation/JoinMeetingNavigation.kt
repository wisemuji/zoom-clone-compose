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
