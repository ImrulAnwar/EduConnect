package com.imrul.educonnect.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.imrul.educonnect.core.Routes.Companion.COURSES_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.LOGIN_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.MESSAGES_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.PROFILE_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.REGISTER_SCREEN_ROUTE
import com.imrul.educonnect.presentation.screen_courses.CoursesScreen
import com.imrul.educonnect.presentation.login.LoginScreen
import com.imrul.educonnect.presentation.login.model.LoginState
import com.imrul.educonnect.presentation.register.RegisterScreen
import com.imrul.educonnect.presentation.screen_messages.MessagesScreen
import com.imrul.educonnect.presentation.screen_profile.ProfileScreen

@Composable
fun NavGraph(
    loginState: LoginState?,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = if (loginState?.user != null) COURSES_SCREEN_ROUTE else LOGIN_SCREEN_ROUTE
    ) {
        composable(
            route = LOGIN_SCREEN_ROUTE
        ) {
            LoginScreen(navController = navController)
        }
        composable(
            route = REGISTER_SCREEN_ROUTE
        ) {
            RegisterScreen(navController = navController)
        }
        composable(
            route = COURSES_SCREEN_ROUTE
        ) {
            CoursesScreen(navController = navController)
        }
        composable(
            route = MESSAGES_SCREEN_ROUTE
        ) {
            MessagesScreen(navController = navController)
        }
        composable(
            route = PROFILE_SCREEN_ROUTE
        ) {
            ProfileScreen(navController = navController)
        }
    }
}