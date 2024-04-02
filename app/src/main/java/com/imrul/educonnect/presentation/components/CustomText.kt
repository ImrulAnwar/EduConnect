package com.imrul.educonnect.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.imrul.educonnect.ui.theme.Maroon70
import com.imrul.educonnect.ui.theme.poppinsFontFamily

@Composable
fun CustomText(text: String, size: TextUnit, fontWeight: FontWeight, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(
            fontWeight = fontWeight,
            fontSize = size,
            fontFamily = poppinsFontFamily
        ),
        color = Maroon70,
        modifier = modifier
    )
}