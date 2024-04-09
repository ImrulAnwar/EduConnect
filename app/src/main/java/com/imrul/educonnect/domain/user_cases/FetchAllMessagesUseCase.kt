package com.imrul.educonnect.domain.user_cases

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.Query
import com.imrul.educonnect.core.Resource
import com.imrul.educonnect.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchAllMessagesUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {

    operator fun invoke(): Flow<Resource<Query>> = flow {
        try {
            emit(Resource.Loading())
            repository.fetchAllMessages().apply {
                emit(Resource.Success(this))
            }
        } catch (e: FirebaseAuthException) {
            emit(Resource.Error(message = e.message.toString()))
        } catch (e: FirebaseNetworkException) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}