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
