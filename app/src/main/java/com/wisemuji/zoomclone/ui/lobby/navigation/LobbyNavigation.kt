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
