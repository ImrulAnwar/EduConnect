package com.imrul.educonnect.data.repository

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.Query
import com.imrul.educonnect.domain.model.User
import com.imrul.educonnect.domain.network.AuthenticationDataSource
import com.imrul.educonnect.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow

class AuthenticationRepoImplementation(private val dataSource: AuthenticationDataSource) :
    AuthenticationRepository {
    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        dataSource.signIn(email, password)

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): FirebaseUser? = dataSource.createUser(username, email, password)

    override suspend fun getUsers(uid: String?): Flow<MutableList<User>> = dataSource.getUsers(uid)

    override suspend fun getUser(uid: String?): User? = dataSource.getUser(uid)
    override suspend fun sendMessage(
        senderId: String?,
        receiverId: String?,
        message: String?,
    ) {
        dataSource.sendMessage(
            senderId = senderId,
            receiverId = receiverId,
            message = message,
        )
    }

    override fun currentUser(): FirebaseUser? = dataSource.currentUser()
    override suspend fun fetchItems(id1: String?, id2: String?): Query =
        dataSource.fetchItems(id1 = id1, id2 = id2)

    override fun signOut() = dataSource.signOut()
}