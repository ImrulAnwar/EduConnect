package com.imrul.educonnect.ui.presentation.register.model

import com.google.firebase.auth.FirebaseUser

data class RegisterState(
    val isLoading: Boolean = false,
    var user: FirebaseUser? = null,
    val error: String = ""
)