package com.imrul.educonnect.domain.user_cases

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.imrul.educonnect.core.Resource
import com.imrul.educonnect.domain.repository.AuthenticationRepository
import com.imrul.educonnect.presentation.screen_send_message.model.Message
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchMessagesUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(
        senderId: String?,
        receiverId: String?
    ): Flow<Resource<MutableList<Message>>> = flow {
        try {
            emit(Resource.Loading())
            repository.fetchItems(id1 = senderId, id2 = receiverId).apply {
                emit(Resource.Success(this))
            }
        } catch (e: FirebaseAuthException) {
            emit(Resource.Error(message = e.message.toString()))
        } catch (e: FirebaseNetworkException) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}