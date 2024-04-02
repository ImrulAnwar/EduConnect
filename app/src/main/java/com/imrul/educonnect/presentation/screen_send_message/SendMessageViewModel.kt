package com.imrul.educonnect.presentation.screen_send_message

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.imrul.educonnect.core.Resource
import com.imrul.educonnect.domain.user_cases.GetUserUseCase
import com.imrul.educonnect.domain.user_cases.SendMessageUseCase
import com.imrul.educonnect.presentation.screen_login.model.LoginState
import com.imrul.educonnect.presentation.screen_login.model.UserState
import com.imrul.educonnect.presentation.screen_register.model.RegisterState
import com.imrul.educonnect.presentation.screen_send_message.model.MessageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendMessageViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    var sendMessageText by mutableStateOf("")
        private set

    // User State for get last user data
    private val _textReceiverUserState = MutableStateFlow(UserState())
    val textReceiverUserState = _textReceiverUserState.asStateFlow()

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    private val _messageState = MutableStateFlow(MessageState())
    val messageState = _messageState.asStateFlow()

    fun onSendMessageTextChanged(value: String) {
        sendMessageText = value
    }

    private fun clearActions() {
//        MessageState(message = null, isLoading = false, error = null)
        sendMessageText = ""
    }

    fun getUser(uid: String) {
        getUserUseCase(uid).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _textReceiverUserState.value = UserState(user = result.data)
                }

                is Resource.Error -> {
                    _textReceiverUserState.value = UserState(error = result.data.toString())
                }

                is Resource.Loading -> {
                    _textReceiverUserState.value = UserState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun sendMessage(
        senderId: String?,
        receiverId: String?,
        message: String?,
        timestamp: Timestamp
    ) = viewModelScope.launch {
        sendMessageUseCase(
            senderId = senderId,
            receiverId = receiverId,
            message = message,
            timestamp = timestamp
        ).collect { result ->
            when (result) {
                is Resource.Success -> {
                    // message ta kottheke nibo oita ekta question
                    _messageState.value = MessageState(message = message, isLoading = false)
                    clearActions()

                }

                is Resource.Error -> {
                    _messageState.value =
                        MessageState(error = result.message.toString(), isLoading = false)
                }

                is Resource.Loading -> {
                    _messageState.value = MessageState(isLoading = true)
                }
            }
        }
    }
}