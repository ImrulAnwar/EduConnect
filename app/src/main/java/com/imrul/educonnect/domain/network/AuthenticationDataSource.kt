package com.imrul.educonnect.domain.network

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.Query
import com.imrul.educonnect.domain.model.User
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

    suspend fun fetchAllMessages(order: Query.Direction): Query
    fun currentUser(): FirebaseUser?

    fun signOut()
}