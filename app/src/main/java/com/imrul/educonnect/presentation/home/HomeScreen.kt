package com.imrul.educonnect.presentation.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.imrul.educonnect.core.Constants
import com.imrul.educonnect.core.Routes
import com.imrul.educonnect.presentation.login.LoginViewModel
import com.imrul.educonnect.ui.theme.Maroon80
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val loginState = viewModel.loginState.value

    LaunchedEffect(loginState) {
//        delay(1000)
//        if (loginState.user == null) navController.navigate(Routes.LOGIN_SCREEN_ROUTE)
    }

    Column {

        Button(
            onClick = { viewModel.signOut() },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Maroon80)
        ) {
            Text(text = Constants.SIGN_OUT, fontSize = 14.sp)
        }
        Button(
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Maroon80),
            onClick = {
               viewModel.postError()
            }
        ) {
            Text(text = "post error", fontSize = 14.sp)
        }
    }
}