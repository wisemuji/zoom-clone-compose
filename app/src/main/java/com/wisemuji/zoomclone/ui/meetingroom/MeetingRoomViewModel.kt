package com.wisemuji.zoomclone.ui.meetingroom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wisemuji.zoomclone.ui.meetingroom.navigation.MeetingRoomArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.video.android.core.Call
import io.getstream.video.android.core.StreamVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MeetingRoomViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val meetingArgs: MeetingRoomArgs = MeetingRoomArgs(savedStateHandle)
    private val callId = meetingArgs.callId

    private val _uiState =
        MutableStateFlow<MeetingUiState>(MeetingUiState.Loading)
    val uiState: StateFlow<MeetingUiState> = _uiState

    fun loadMeeting(type: String = DEFAULT_TYPE, id: String = callId) {
        viewModelScope.launch {
            val streamVideo = StreamVideo.instance()
            val call = streamVideo.call(type = type, id = id)
            val result = call.join(create = true)
            result.onSuccess {
                _uiState.value = MeetingUiState.Success(call)
            }.onError {
                _uiState.value = MeetingUiState.Error
            }
        }
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
