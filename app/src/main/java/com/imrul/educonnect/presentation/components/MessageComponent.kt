package com.imrul.educonnect.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.imrul.educonnect.presentation.screen_send_message.model.Message
import com.imrul.educonnect.ui.theme.Maroon70

@Composable
fun MessageComponent(
    modifer: Modifier = Modifier,
    color: Color = Maroon70,
    message: String
) {
    Box(modifier = modifer) {
        Text(text = message)
    }
}