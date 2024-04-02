package com.imrul.educonnect.presentation.screen_send_message

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.imrul.educonnect.domain.user_cases.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SendMessageViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {
    var sendMessageText by mutableStateOf("")
        private set

    fun onSendMessageTextChanged(value: String) {
        sendMessageText = value
    }
}