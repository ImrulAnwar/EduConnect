package com.imrul.educonnect.presentation.screen_send_message

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imrul.educonnect.core.Resource
import com.imrul.educonnect.domain.user_cases.GetUserUseCase
import com.imrul.educonnect.domain.user_cases.SendMessageUseCase
import com.imrul.educonnect.presentation.screen_login.model.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    fun onSendMessageTextChanged(value: String) {
        sendMessageText = value
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
}