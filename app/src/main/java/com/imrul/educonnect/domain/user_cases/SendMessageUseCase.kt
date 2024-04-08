package com.imrul.educonnect.domain.user_cases

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuthException
import com.imrul.educonnect.core.Resource
import com.imrul.educonnect.domain.repository.AuthenticationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(
        senderId: String?,
        receiverId: String?,
        message: String?,
    ): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading())
            val sendMessage = repository.sendMessage(
                senderId = senderId,
                receiverId = receiverId,
                message = message,
            )

            emit(Resource.Success(sendMessage))

        } catch (e: FirebaseAuthException) {
            emit(Resource.Error(e.message.toString()))
        } catch (e: FirebaseNetworkException) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}