package com.imrul.educonnect.presentation.screen_login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imrul.educonnect.R
import com.imrul.educonnect.core.Constants
import com.imrul.educonnect.core.Constants.Companion.EMAIL_PLACEHOLDER
import com.imrul.educonnect.core.Routes.Companion.COURSES_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.REGISTER_SCREEN_ROUTE
import com.imrul.educonnect.presentation.components.OAuthButton
import com.imrul.educonnect.presentation.components.PasswordTextField
import com.imrul.educonnect.presentation.components.RegularTextField
import com.imrul.educonnect.ui.theme.Maroon10
import com.imrul.educonnect.ui.theme.Maroon70
import com.imrul.educonnect.ui.theme.Maroon80

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val emailText = viewModel.emailText
    val passwordText = viewModel.passwordText

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    val annotatedString = buildAnnotatedString {
        append(Constants.DONT_HAVE_AN_ACCOUNT)
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
        append(Constants.SIGN_UP)
        pop()
    }

    val loginState by viewModel.loginState.collectAsState()
    val context = LocalContext.current
    val userState by viewModel.userState.collectAsState()
    // what is it doing?

    LaunchedEffect(loginState) {
        viewModel.currentUser()
        if (loginState.user != null) {
            navController.popBackStack()
            navController.navigate(COURSES_SCREEN_ROUTE)
        }
        loginState.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        loginState.user?.let { user ->
            viewModel.getUser(user.uid)
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Maroon10) // Set the background color here
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RegularTextField(
                text = emailText,
                onValueChange = { viewModel.onEmailChanged(it) },
                label = EMAIL_PLACEHOLDER
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextField(
                password = passwordText,
                passwordVisibility = passwordVisibility,
                onPasswordChange = { viewModel.onPasswordChanged(it) },
                onPasswordVisibilityChange = { passwordVisibility = it },
                Constants.PASSWORD_PLACEHOLDER
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    viewModel.loginUser()
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Maroon80)
            ) {
                Text(text = Constants.SIGN_IN, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            OAuthButton(
                iconDrawableId = R.drawable.google_logo,
                text = Constants.SIGN_IN_WITH,
                onClick = {
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = annotatedString,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        navController.navigate(REGISTER_SCREEN_ROUTE)
                    },
                color = Maroon70
            )
        }
    }
}

