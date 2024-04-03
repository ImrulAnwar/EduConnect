package com.imrul.educonnect.presentation.screen_send_message

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.Timestamp
import com.imrul.educonnect.R
import com.imrul.educonnect.core.Constants.Companion.MESSAGE_PLACEHOLDER
import com.imrul.educonnect.presentation.components.CircularImage
import com.imrul.educonnect.presentation.components.CustomIcon
import com.imrul.educonnect.presentation.components.CustomText
import com.imrul.educonnect.presentation.components.MessageComponent
import com.imrul.educonnect.presentation.components.RegularTextField
import com.imrul.educonnect.presentation.screen_login.LoginViewModel
import com.imrul.educonnect.ui.theme.Maroon20
import com.imrul.educonnect.ui.theme.Maroon70
import java.util.Date

@Composable
fun SendMessageScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(),
    sendMessageViewModel: SendMessageViewModel = hiltViewModel(),
    receiverUid: String?
) {
    val sendMessageText = sendMessageViewModel.sendMessageText

    val userProfileImage: Painter = painterResource(id = R.drawable.profile_image_placeholder)
    val textReceiverUserState by sendMessageViewModel.textReceiverUserState.collectAsState()
    val loginState by loginViewModel.loginState.collectAsState()
    val messageState by sendMessageViewModel.messageState.collectAsState()
    val messagesState by sendMessageViewModel.messagesState.collectAsState()

    val listState = rememberLazyListState()

    LaunchedEffect(loginState) {
        loginViewModel.currentUser()
    }
    LaunchedEffect(receiverUid) {
        receiverUid?.let {
            sendMessageViewModel.getUser(it)
        }
    }


    LaunchedEffect(loginState, textReceiverUserState) {
//        sendMessageViewModel.getMessages(
//            senderId = loginState.user?.uid,
//            receiverId = textReceiverUserState.user?.uid
//        )
        sendMessageViewModel.fetchItems(
            id1 = loginState.user?.uid,
            id2 = textReceiverUserState.user?.uid
        )
    }
    if (messagesState.isNotEmpty()) {
        LaunchedEffect(listState, messagesState) {
            Log.d("problem", "SendMessageScreen: ${messagesState.size}")
            listState.scrollToItem(messagesState.size - 1)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // top part
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            CustomIcon(
                painter = rememberVectorPainter(image = Icons.AutoMirrored.Filled.ArrowBack),
                contentDescription = "back_icon",
                onClick = { navController.popBackStack() }
            )
            Spacer(modifier = Modifier.width(10.dp))
            CircularImage(painter = userProfileImage, size = 40.dp)
            Spacer(modifier = Modifier.width(10.dp))
            CustomText(
                text = textReceiverUserState.user?.displayName ?: "Username",
                size = 15.sp,
                fontWeight = FontWeight.Bold
            )
            //divides them to left and right
            Spacer(modifier = Modifier.weight(1f))
            CustomIcon(
                painter = rememberVectorPainter(image = Icons.Filled.MoreVert),
                contentDescription = "info_icon",
                onClick = {}
            )
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = listState
        ) {
            items(messagesState) { message ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (message.senderId == loginState.user?.uid)
                        Arrangement.End else Arrangement.Start
                ) {
                    MessageComponent(
                        message = message.message,
                        color = if (message.senderId == loginState.user?.uid) Maroon70 else Maroon20,
                        textcolor = if (message.senderId == loginState.user?.uid) Maroon20 else Maroon70,
                    )
                }
            }
        }
        // bottom part
        Spacer(modifier = Modifier.width(5.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            CustomIcon(
                painter = painterResource(R.drawable.attach_file_icon),
                contentDescription = "attach_file_icon",
                onClick = {}
            )
            Spacer(modifier = Modifier.width(5.dp))
            RegularTextField(
                text = sendMessageText,
                onValueChange = { sendMessageViewModel.onSendMessageTextChanged(it) },
                label = MESSAGE_PLACEHOLDER,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(5.dp))

            if (messageState.isLoading)
                CircularProgressIndicator(color = Maroon70)
            else
                CustomIcon(
                    painter = rememberVectorPainter(image = Icons.AutoMirrored.Filled.Send),
                    contentDescription = "send_message_icon",
                    onClick = {
                        if (sendMessageText.isNotBlank()) {
                            sendMessageViewModel.sendMessage(
                                senderId = loginState.user?.uid,
                                message = sendMessageText,
                                receiverId = textReceiverUserState.user?.uid,
                            )
                        }
                    }
                )
        }
    }
}