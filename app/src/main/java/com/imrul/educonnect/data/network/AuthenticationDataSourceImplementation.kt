package com.imrul.educonnect.data.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.imrul.educonnect.core.Constants.Companion.USERS_COLLECTION
import com.imrul.educonnect.domain.model.User
import com.imrul.educonnect.domain.network.AuthenticationDataSource
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

//It is handling authentication, login & register credentials, retrieves user info

class AuthenticationDataSourceImplementation(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
): AuthenticationDataSource {
    override suspend fun signIn(email: String, password: String): FirebaseUser? =
        auth
            .signInWithEmailAndPassword(email, password)
            .await()
            .user

    override suspend fun createUser(
        username: String,
        email: String,
        password: String
    ): FirebaseUser? = auth
    .createUserWithEmailAndPassword(email, password)
    .await()
    .user.apply {
        createUserForFirestore(
            username = username,
            uid = this?.uid
        )
    }
    private suspend fun createUserForFirestore(username: String, uid: String?) {
        uid?.let {
            val data = hashMapOf(
                "uid" to uid,
                "displayName" to username
            )
            fireStore
                .collection(USERS_COLLECTION)
                .document(uid)
                .set(data)
                .await()
        }
    }

    // Get user data with Firestore users collection
    override suspend fun getUser(uid: String?): User? =
        uid?.let {
            fireStore.collection(USERS_COLLECTION)
                .document(it)
                .get()
                .await()
                .toObject(User::class.java)
        }

    // Get users data with Firestore users collection
    override suspend fun getUsers(uid: String?): Flow<MutableList<User>> =
        fireStore.collection(USERS_COLLECTION)
            .whereEqualTo("uid", uid)
            .snapshotFlow().map { it.toObjects(User::class.java) }

    // Additional Firebase Functions
    private fun Query.snapshotFlow(): Flow<QuerySnapshot> = callbackFlow {
        val listenerRegistration = addSnapshotListener { value, error ->
            if (error != null) {
                close()
                return@addSnapshotListener
            }
            if (value != null)
                trySend(value)
        }
        awaitClose {
            listenerRegistration.remove()
        }
    }

    override fun currentUser(): FirebaseUser? = auth.currentUser

    override fun signOut() = auth.signOut()

}