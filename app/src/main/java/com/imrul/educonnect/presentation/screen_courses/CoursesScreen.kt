package com.imrul.educonnect.presentation.screen_courses

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imrul.educonnect.core.Constants
import com.imrul.educonnect.presentation.components.CustomText
import com.imrul.educonnect.presentation.screen_login.LoginViewModel
import com.imrul.educonnect.ui.theme.poppinsFontFamily

@Composable
fun CoursesScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    CustomText(
        text = Constants.COURSES_PLACEHOLDER,
        size = 25.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
    )
    Spacer(modifier = Modifier.width(8.dp))
}

