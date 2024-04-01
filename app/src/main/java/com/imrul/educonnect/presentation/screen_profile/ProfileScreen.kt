package com.imrul.educonnect.presentation.screen_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imrul.educonnect.R
import com.imrul.educonnect.core.Constants
import com.imrul.educonnect.presentation.components.CircularImage
import com.imrul.educonnect.presentation.login.LoginViewModel
import com.imrul.educonnect.ui.theme.Maroon20
import com.imrul.educonnect.ui.theme.Maroon80

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        val loginState by viewModel.loginState.collectAsState()

        LaunchedEffect(loginState) {
            viewModel.currentUser()
        }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularImage(
                painter = painterResource(id = R.drawable.profile_image_placeholder),
                size = 150.dp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(Maroon20, shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = loginState.user?.displayName.toString(),
                        modifier = Modifier.padding(20.dp, 0.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "212002912",
                        modifier = Modifier.padding(20.dp, 0.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "9th Semester",
                        modifier = Modifier.padding(20.dp, 0.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = loginState.user?.email.toString(),
                        modifier = Modifier.padding(20.dp, 0.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

            }
            Text(
                text = "Edit Profile",
                style = TextStyle(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    viewModel.signOut(navController)
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Maroon80)
            ) {
                Text(text = Constants.SIGN_OUT, fontSize = 14.sp)
            }
        }
    }
}