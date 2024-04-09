package com.imrul.educonnect.presentation.screen_messages

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.imrul.educonnect.core.Resource
import com.imrul.educonnect.domain.user_cases.FetchAllMessagesUseCase
import com.imrul.educonnect.domain.user_cases.GetUserUseCase
import com.imrul.educonnect.domain.user_cases.GetUsersUseCase
import com.imrul.educonnect.presentation.screen_login.LoginViewModel
import com.imrul.educonnect.presentation.screen_login.model.LoginState
import com.imrul.educonnect.presentation.screen_login.model.UserState
import com.imrul.educonnect.presentation.screen_messages.model.Conversation
import com.imrul.educonnect.presentation.screen_send_message.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val fetchAllMessagesUseCase: FetchAllMessagesUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _allConversations = mutableStateListOf<Conversation>()
    val allConversations = _allConversations

    private val _textReceiverUserState = MutableStateFlow(UserState())
    val textReceiverUserState = _textReceiverUserState.asStateFlow()
    fun fetchAllMessagesOfaUser(

    ) = fetchAllMessagesUseCase().onEach { result ->
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

                    for ((otherUid, latestMessage) in messagesMap) {
                        if (otherUid != null) {
                            getUser(otherUid)
                        }
                        val conversation = Conversation(
                            username = textReceiverUserState.value.user?.displayName,
                            latestMessage = latestMessage,
                            otherUID = otherUid
                        )
                        _allConversations.add(conversation)
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
//
//    fun getUsers(uid: String? = _loginState.value.user?.uid) =
//        getUsersUseCase(uid).onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    result.data?.let { list ->
//                        list.forEach { user ->
//                            _usersState.add(user)
//                        }
//                    }
//                }
//
//                is Resource.Error -> {
////                    _usersState.value = UserState(error = result.message.toString())
//                }
//
//                is Resource.Loading -> {
//
////                    _userState.value = UserState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
}