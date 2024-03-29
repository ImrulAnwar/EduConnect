package com.imrul.educonnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.imrul.educonnect.presentation.login.LoginViewModel
import com.imrul.educonnect.presentation.login.model.LoginState
import com.imrul.educonnect.presentation.navigation.NavGraph
import com.imrul.educonnect.ui.theme.EduConnectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private var loginState: LoginState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.currentUser()
        loginState = viewModel.loginState.value
        setContent {
            EduConnectTheme {
                NavGraph(loginState)
            }
        }
    }
}