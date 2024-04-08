package com.imrul.educonnect.data.network

import android.util.Log
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app
import com.google.firestore.v1.DocumentTransform.FieldTransform.ServerValue
import com.imrul.educonnect.core.Constants.Companion.MESSAGES_COLLECTION
import com.imrul.educonnect.core.Constants.Companion.USERS_COLLECTION
import com.imrul.educonnect.domain.model.User
import com.imrul.educonnect.domain.network.AuthenticationDataSource
import com.imrul.educonnect.presentation.screen_send_message.model.Message
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import java.sql.Types.TIMESTAMP
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//It is handling authentication, login & register credentials, retrieves user info
class AuthenticationDataSourceImplementation(
    private val auth: FirebaseAuth,
    private val fireStore: FirebaseFirestore
) : AuthenticationDataSource {
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
                uid = this?.uid,
                email = email
            )
        }

    private suspend fun createUserForFirestore(username: String, uid: String?, email: String?) {
        uid?.let {
            val data = hashMapOf(
                "uid" to uid,
                "displayName" to username,
                "email" to email
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

    override suspend fun sendMessage(
        senderId: String?,
        receiverId: String?,
        message: String?,
    ) {
        senderId?.let { sender ->
            receiverId?.let { receiver ->
                message?.let { msg ->
                    val data = hashMapOf(
                        "senderId" to sender,
                        "receiverId" to receiver,
                        "message" to msg,
                        "timestamp" to serverTimestamp()// Use serverTimestamp
                    )
                    fireStore
                        .collection(MESSAGES_COLLECTION)
                        .document()
                        .set(data)
                        .await()
                }
            }
        }
    }


    // Get users data with Firestore users collection
    override suspend fun getUsers(uid: String?): Flow<MutableList<User>> {
        // Check if uid is null to determine the query logic
        val query = if (uid != null) {
            fireStore.collection(USERS_COLLECTION)
                .whereNotEqualTo("uid", uid)
        } else {
            fireStore.collection(USERS_COLLECTION)
                .whereNotEqualTo("uid", auth.currentUser?.uid)
        }

        return query.snapshotFlow()
            .map { snapshot ->
                snapshot.documents.mapNotNull { document ->
                    document.toObject(User::class.java)
                }.toMutableList()
            }
    }

    override suspend fun fetchItems(id1: String?, id2: String?): Query = fireStore.collection(MESSAGES_COLLECTION)
        .orderBy("timestamp", Query.Direction.ASCENDING)


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


