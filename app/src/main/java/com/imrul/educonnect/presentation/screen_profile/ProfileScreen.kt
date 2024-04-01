package com.imrul.educonnect.presentation.screen_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imrul.educonnect.core.Constants
import com.imrul.educonnect.presentation.login.LoginViewModel
import com.imrul.educonnect.ui.theme.Maroon80

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        val loginState by viewModel.loginState.collectAsState()

        LaunchedEffect(loginState) {
            viewModel.currentUser()
        }



        Column {
            Button(
                onClick = {
                    viewModel.signOut(navController)
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Maroon80)
            ) {
                Text(text = Constants.SIGN_OUT, fontSize = 14.sp)
            }
            Text(loginState.user?.displayName.toString())
        }
    }
}