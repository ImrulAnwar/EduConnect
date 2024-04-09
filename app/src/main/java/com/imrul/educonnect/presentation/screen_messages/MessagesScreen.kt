package com.imrul.educonnect.presentation.screen_messages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imrul.educonnect.core.Constants.Companion.MESSAGES_PLACEHOLDER
import com.imrul.educonnect.core.Routes.Companion.SEND_MESSAGE_SCREEN_ROUTE
import com.imrul.educonnect.presentation.components.ConversationComponent
import com.imrul.educonnect.presentation.components.CustomText
import com.imrul.educonnect.presentation.components.UserComponent
import com.imrul.educonnect.presentation.screen_login.LoginViewModel

@Composable
fun MessagesScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    messageViewModel: MessageViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getUsers()
    }
    LaunchedEffect(Unit) {
        messageViewModel.fetchAllMessagesOfaUser()
    }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp, 0.dp, 80.dp)
    ) {
        CustomText(
            text = MESSAGES_PLACEHOLDER,
            size = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp)
        )
        LazyRow {
            items(viewModel.usersState.toList()) { user ->
                user?.let {
                    UserComponent(username = it.displayName, size = 80.dp, onClick = {
                        // sending uid to send message screen
                        navController.navigate("$SEND_MESSAGE_SCREEN_ROUTE/${it.uid}")
                    })
                }
            }
        }

        LazyColumn {
            items(messageViewModel.allConversations.toList()) { conversation ->
                ConversationComponent(
                    username = conversation.username,
                    latestMessage = conversation.latestMessage,
                    onClick = {
                        navController.navigate("$SEND_MESSAGE_SCREEN_ROUTE/${conversation.otherUID}")
                    }
                )
            }
        }
    }
}