package com.imrul.educonnect.presentation.register

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imrul.educonnect.R
import com.imrul.educonnect.core.Constants.Companion.ALREADY_HAVE_AN_ACCOUNT
import com.imrul.educonnect.core.Constants.Companion.CONFIRM_PASSWORD_PLACEHOLDER
import com.imrul.educonnect.core.Constants.Companion.EMAIL_PLACEHOLDER
import com.imrul.educonnect.core.Constants.Companion.PASSWORD_PLACEHOLDER
import com.imrul.educonnect.core.Constants.Companion.SIGN_IN
import com.imrul.educonnect.core.Constants.Companion.SIGN_UP
import com.imrul.educonnect.core.Constants.Companion.SIGN_UP_WITH
import com.imrul.educonnect.core.Constants.Companion.USERNAME_PLACEHOLDER
import com.imrul.educonnect.presentation.components.OAuthButton
import com.imrul.educonnect.presentation.components.PasswordTextField
import com.imrul.educonnect.presentation.components.RegularTextField
import com.imrul.educonnect.ui.theme.Maroon10
import com.imrul.educonnect.ui.theme.Maroon70
import com.imrul.educonnect.ui.theme.Maroon80


@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = hiltViewModel()) {
    val usernameText = viewModel.usernameText
    val emailText = viewModel.emailText
    val passwordText = viewModel.passwordText
    val confirmPasswordText = viewModel.confirmPasswordText

    var passwordVisibility by remember { mutableStateOf(false) }

    // to make the last part bold
    val annotatedString = buildAnnotatedString {
        append(ALREADY_HAVE_AN_ACCOUNT)
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
        append(SIGN_IN)
        pop()
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
                text = usernameText,
                onValueChange = { viewModel.onUsernameChanged(it) },
                label = USERNAME_PLACEHOLDER
            )
            Spacer(modifier = Modifier.height(20.dp))
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
                PASSWORD_PLACEHOLDER
            )
            Spacer(modifier = Modifier.height(20.dp))
            PasswordTextField(
                password = confirmPasswordText,
                passwordVisibility = passwordVisibility,
                onPasswordChange = { viewModel.onConfirmPasswordChanged(it) },
                onPasswordVisibilityChange = { passwordVisibility = it },
                CONFIRM_PASSWORD_PLACEHOLDER
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { viewModel.registerUser() },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Maroon80)
            ) {
                Text(text = SIGN_UP, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            OAuthButton(
                iconDrawableId = R.drawable.google_logo,
                text = SIGN_UP_WITH,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = annotatedString,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        navController.popBackStack()
                    },
                color = Maroon70
            )
        }
    }
}