package com.imrul.educonnect.presentation.screen_profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProfileScreen(navController: NavController){
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Profile Screen", modifier = Modifier
            .fillMaxSize()
            .padding(16.dp))
        Spacer(modifier = Modifier.width(8.dp))

    }
}