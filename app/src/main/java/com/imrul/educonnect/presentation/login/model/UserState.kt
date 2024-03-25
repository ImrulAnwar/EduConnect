package com.imrul.educonnect.presentation.login.model

import com.imrul.educonnect.domain.model.User

data class UserState(
    val error: String = "",
    val user: User? = null,
    val users: List<User?>? = null,
    val isLoading: Boolean = false
)