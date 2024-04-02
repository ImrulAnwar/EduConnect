package com.imrul.educonnect.presentation.screen_send_message.model


data class MessageState(
    val isLoading: Boolean = false,
    var message: String? = null,
    val error: String? = null
)
