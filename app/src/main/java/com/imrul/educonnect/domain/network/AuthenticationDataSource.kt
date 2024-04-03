package com.imrul.educonnect.domain.network

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import com.imrul.educonnect.domain.model.User
import com.imrul.educonnect.presentation.screen_send_message.model.Message
import kotlinx.coroutines.flow.Flow

interface AuthenticationDataSource {
    suspend fun signIn(email: String, password: String): FirebaseUser?

    suspend fun createUser(username: String, email: String, password: String): FirebaseUser?

    suspend fun getUsers(uid: String?): Flow<MutableList<User>>

    suspend fun getUser(uid: String?): User?

    suspend fun sendMessage(
        senderId: String?,
        receiverId: String?,
        message: String?,
    )

    suspend fun getMessages(
        senderId: String?,
        receiverId: String?
    ): Flow<MutableList<Message>>


    fun currentUser(): FirebaseUser?

    fun signOut()
}