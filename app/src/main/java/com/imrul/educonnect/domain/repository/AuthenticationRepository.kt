package com.imrul.educonnect.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.Query
import com.imrul.educonnect.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String): FirebaseUser?

    suspend fun register(username: String, email: String, password: String): FirebaseUser?

    suspend fun getUsers(uid: String?): Flow<MutableList<User>>

    suspend fun getUser(uid: String?): User?

    suspend fun sendMessage(
        senderId: String?,
        receiverId: String?,
        message: String?,
    )

    fun currentUser(): FirebaseUser?

    suspend fun fetchAllMessages(): Query
    fun signOut()
}