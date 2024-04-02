package com.imrul.educonnect.domain.user_cases

import com.imrul.educonnect.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
}