package com.imrul.educonnect.presentation.screen_send_message.model

import com.google.firebase.Timestamp
import java.util.Date

data class Message(
    val message: String = "",
    val senderId: String = "",
    val receiverId: String = "",
)
