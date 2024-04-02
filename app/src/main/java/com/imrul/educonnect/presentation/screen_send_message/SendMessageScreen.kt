package com.imrul.educonnect.presentation.screen_send_message

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imrul.educonnect.presentation.screen_login.LoginViewModel

@Composable
fun SendMessageScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Text(
        text = "Send Message Screen", modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    Spacer(modifier = Modifier.width(8.dp))
}