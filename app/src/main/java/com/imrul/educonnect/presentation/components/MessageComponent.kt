package com.imrul.educonnect.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.imrul.educonnect.ui.theme.Maroon20
import com.imrul.educonnect.ui.theme.Maroon70

@Composable
fun MessageComponent(
    modifer: Modifier = Modifier,
    color: Color = Maroon70,
    textcolor: Color = Maroon20,
    message: String,
) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .background(color, shape = RoundedCornerShape(10.dp)),
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = message,
            color = textcolor
        )
    }
}