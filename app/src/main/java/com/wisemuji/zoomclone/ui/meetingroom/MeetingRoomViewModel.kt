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

package com.wisemuji.zoomclone.ui.meetingroom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisemuji.zoomclone.model.MeetingOptions
import com.wisemuji.zoomclone.ui.meetingroom.navigation.MeetingRoomRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.StreamVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class MeetingRoomViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val meetingOptions = savedStateHandle
        .get<String>(MeetingRoomRoute.ARGS_MEETING_OPTIONS)
        ?.let { Json.decodeFromString<MeetingOptions>(it) }
        ?: error("MeetingOptions not found")

    private val _uiState =
        MutableStateFlow<MeetingUiState>(MeetingUiState.Loading)
    val uiState: StateFlow<MeetingUiState> = _uiState

    fun loadMeeting(
        type: String = DEFAULT_TYPE,
        meetingOptions: MeetingOptions = this.meetingOptions,
    ) {
        updateUsername(meetingOptions.username)
        val id = meetingOptions.meetingId
        val call = StreamVideo.instance().call(type = type, id = id)
        viewModelScope.launch {
            val result = call.join(create = true)
            result.onSuccess {
                call.applyMeetingOptions(meetingOptions)
                _uiState.value = MeetingUiState.Success(call)
            }.onError {
                _uiState.value = MeetingUiState.Error
            }
        }
    }

    private fun updateUsername(username: String) {
        if (username.isEmpty()) return
        // TODO: Update the user's name
    }

    private fun Call.applyMeetingOptions(meetingOptions: MeetingOptions) {
        camera.setEnabled(meetingOptions.videoOn)
        microphone.setEnabled(meetingOptions.audioOn)
    }

    companion object {
        private const val DEFAULT_TYPE = "default"
    }
}

sealed interface MeetingUiState {
    data object Loading : MeetingUiState
    data class Success(val call: Call) : MeetingUiState
    data object Error : MeetingUiState
}
