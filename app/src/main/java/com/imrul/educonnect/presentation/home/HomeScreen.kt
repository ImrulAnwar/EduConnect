package com.imrul.educonnect.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imrul.educonnect.core.Constants
import com.imrul.educonnect.core.Routes
import com.imrul.educonnect.core.Routes.Companion.LOGIN_SCREEN_ROUTE
import com.imrul.educonnect.presentation.login.LoginViewModel
import com.imrul.educonnect.ui.theme.Maroon80

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val loginState = viewModel.loginState.value
    val userState = viewModel.userState.value
    val context = LocalContext.current

    LaunchedEffect(userState) {
        userState.user?.let { user ->
            viewModel.getUser(user.uid)
        }
    }
    Column {

        Button(
            onClick = {
                viewModel.signOut()
                if (loginState.user == null) navController.navigate(LOGIN_SCREEN_ROUTE)
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Maroon80)
        ) {
            Text(text = Constants.SIGN_OUT, fontSize = 14.sp)
        }
        Text(userState.user?.uid.toString())
    }
}