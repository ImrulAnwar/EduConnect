package com.imrul.educonnect.presentation.register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
import dagger.hilt.android.qualifiers.ApplicationContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen() {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val annotatedString = buildAnnotatedString {
        append(ALREADY_HAVE_AN_ACCOUNT)
        pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
        append(SIGN_IN)
        pop()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White) // Set the background color here
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                singleLine = true,
                label = { Text(USERNAME_PLACEHOLDER) },
                shape = RoundedCornerShape(16.dp),
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                value = email,
                onValueChange = { email = it },
                label = { Text(EMAIL_PLACEHOLDER) },
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                value = password,
                onValueChange = { password = it },
                label = { Text(PASSWORD_PLACEHOLDER) }
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                shape = RoundedCornerShape(16.dp),
                singleLine = true,
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text(CONFIRM_PASSWORD_PLACEHOLDER) }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = SIGN_UP)
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
                        Log.d("TAG", "RegisterScreen: ")
                    }
            )

        }
    }

}