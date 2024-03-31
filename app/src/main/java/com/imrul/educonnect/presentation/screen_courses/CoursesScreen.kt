package com.imrul.educonnect.presentation.screen_courses

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.imrul.educonnect.core.Constants
import com.imrul.educonnect.core.Routes.Companion.COURSES_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.MESSAGES_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.PROFILE_SCREEN_ROUTE
import com.imrul.educonnect.presentation.login.LoginViewModel
import com.imrul.educonnect.presentation.navigation.BottomBarScreens
import com.imrul.educonnect.presentation.navigation.NavGraph
import com.imrul.educonnect.ui.theme.Maroon80

@SuppressLint("StateFlowValueCalledInComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CoursesScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val loginState by viewModel.loginState.collectAsState()

    LaunchedEffect(loginState) {
        viewModel.currentUser()
    }



    Column {
        Button(
            onClick = {
                viewModel.signOut(navController)
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Maroon80)
        ) {
            Text(text = Constants.SIGN_OUT, fontSize = 14.sp)
        }
        Text(loginState.user?.displayName.toString())
    }

}