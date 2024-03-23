package com.imrul.educonnect.domain.network

import com.google.firebase.auth.FirebaseUser
import com.imrul.educonnect.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthenticationDataSource {
    suspend fun signIn(email: String, password: String): FirebaseUser?

    suspend fun createUser(username: String, email: String, password: String): FirebaseUser?

    suspend fun getUsers(uid: String?): Flow<MutableList<User>>

    suspend fun getUser(uid: String?): User?

    fun currentUser(): FirebaseUser?

    fun signOut()
}