package com.imrul.educonnect.presentation.screen_messages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imrul.educonnect.core.Constants
import com.imrul.educonnect.core.Constants.Companion.MESSAGES_PLACEHOLDER
import com.imrul.educonnect.presentation.components.CustomText
import com.imrul.educonnect.presentation.components.UserComponent
import com.imrul.educonnect.presentation.screen_login.LoginViewModel
import com.imrul.educonnect.ui.theme.Maroon80

@Composable
fun MessagesScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.getUsers()
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(
            text = MESSAGES_PLACEHOLDER,
            size = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
        )
        LazyRow {
            items(viewModel.usersState.toList()) { user ->
                user?.displayName?.let {
                    UserComponent(username = it, size = 100.dp) {}
                }
            }
        }
    }
}