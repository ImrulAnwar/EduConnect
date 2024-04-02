package com.imrul.educonnect.presentation.screen_edit_profile

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
fun EditProfileScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Text(
        text = "Edit Profile Screen", modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    Spacer(modifier = Modifier.width(8.dp))
}