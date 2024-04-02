package com.imrul.educonnect.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.imrul.educonnect.R
import com.imrul.educonnect.core.Routes

@Composable
fun UserComponent(
    painter: Painter = painterResource(id = R.drawable.profile_image_placeholder),
    size: Dp = 150.dp,
    username: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(10.dp, 0.dp, 10.dp, 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularImage(painter = painter, size = size)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = username,
            style = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )
    }
}