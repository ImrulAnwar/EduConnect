package com.imrul.educonnect.presentation.screen_login.model

import com.google.firebase.auth.FirebaseUser

data class LoginState(
    val isLoading: Boolean = false,
    var user: FirebaseUser? = null,
    val error: String? = null
)
