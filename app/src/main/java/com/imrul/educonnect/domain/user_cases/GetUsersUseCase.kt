package com.imrul.educonnect.domain.user_cases

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.imrul.educonnect.core.Resource
import com.imrul.educonnect.domain.model.User
import com.imrul.educonnect.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(uid: String?): Flow<Resource<MutableList<User>>> = flow {
        try {
            emit(Resource.Loading())
            repository.getUsers(uid).collect {
                emit(Resource.Success(it))
            }
        } catch (e: FirebaseAuthException) {
            emit(Resource.Error(message = e.message.toString()))
        } catch (e: FirebaseNetworkException) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}