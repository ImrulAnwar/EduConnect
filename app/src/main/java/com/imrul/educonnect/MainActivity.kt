package com.imrul.educonnect

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.imrul.educonnect.presentation.login.LoginViewModel
import com.imrul.educonnect.presentation.login.model.LoginState
import com.imrul.educonnect.presentation.navigation.NavGraph
import com.imrul.educonnect.ui.theme.EduConnectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private var userState: LoginState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.currentUser()
        userState = viewModel.loginState.value
        setContent {
            EduConnectTheme {
                NavGraph(userState)
            }
        }
    }
}