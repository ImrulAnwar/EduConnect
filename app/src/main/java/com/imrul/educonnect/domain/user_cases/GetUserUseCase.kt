package com.imrul.educonnect.domain.user_cases

import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.imrul.educonnect.core.Resource
import com.imrul.educonnect.domain.model.User
import com.imrul.educonnect.domain.repository.AuthenticationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(uid: String?): Flow<Resource<User?>> = flow {
        try {
            emit(Resource.Loading())
            emit(
                Resource.Success(
                    repository.getUser(uid)
                )
            )
        } catch (e: FirebaseException) {
            emit(Resource.Error(message = e.message.toString()))
        } catch (e: FirebaseNetworkException) {
            emit(Resource.Error(message = e.message.toString()))
        }
    }
}