package com.imrul.educonnect.presentation.screen_messages

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.imrul.educonnect.core.Resource
import com.imrul.educonnect.domain.repository.AuthenticationRepository
import com.imrul.educonnect.domain.user_cases.FetchAllMessagesUseCase
import com.imrul.educonnect.domain.user_cases.GetUserUseCase
import com.imrul.educonnect.domain.user_cases.GetUsersUseCase
import com.imrul.educonnect.presentation.screen_login.LoginViewModel
import com.imrul.educonnect.presentation.screen_login.model.LoginState
import com.imrul.educonnect.presentation.screen_login.model.UserState
import com.imrul.educonnect.presentation.screen_messages.model.Conversation
import com.imrul.educonnect.presentation.screen_send_message.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val fetchAllMessagesUseCase: FetchAllMessagesUseCase,
    private val repository: AuthenticationRepository
) : ViewModel() {

    private val _allConversations = mutableStateListOf<Conversation>()
    val allConversations = _allConversations

    fun fetchAllMessagesOfaUser() = fetchAllMessagesUseCase().onEach { result ->
        when (result) {
            is Resource.Success -> {
                // this is logic should be in the useCase or Data Source. but if i use the listener in the useCase it is not updating in realtime.
                // also if this fails app might crash
                result.data?.addSnapshotListener { snapshot, _ ->

                    val conversationList = mutableListOf<Conversation>()
                    val currentUserId = Firebase.auth.currentUser?.uid
                    var otherUserId: String?
                    val messagesMap = mutableMapOf<String?, String?>()
                    for (doc in snapshot!!) {
                        val item = doc.toObject(Message::class.java)
                        otherUserId =
                            if (item.senderId == currentUserId) item.receiverId else item.senderId
                        messagesMap[otherUserId] = item.message
                    }
                    viewModelScope.launch {
                        _allConversations.clear()
                        for ((otherUid, latestMessage) in messagesMap) {
                            val conversation = Conversation(
                                username = repository.getUser(otherUid)?.displayName,
                                latestMessage = latestMessage,
                                otherUID = otherUid
                            )
                            _allConversations.add(conversation)
                        }
                    }
                    Log.d("new problem", "fetchAllMessagesOfaUser: $conversationList")
                }

            }

            is Resource.Error -> {

            }

            is Resource.Loading -> {
            }
        }
    }.launchIn(viewModelScope)
}