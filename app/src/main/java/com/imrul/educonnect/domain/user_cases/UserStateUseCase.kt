package com.imrul.educonnect.domain.user_cases

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.imrul.educonnect.core.Resource
import com.imrul.educonnect.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserStateUseCase @Inject constructor(
    private val repository: AuthenticationRepository
){
    operator fun invoke(): Flow<Resource<FirebaseUser?>> = flow {
        try {
            emit(Resource.Loading())
            when (val user = repository.currentUser()) {
//                null -> {
//                    emit(Resource.Success(null))
//                }
                else -> {
                    emit(Resource.Success(user))
                }
            }
        } catch (e: FirebaseAuthException) {
            emit(Resource.Error(e.message.toString()))
        } catch (e: FirebaseNetworkException) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}