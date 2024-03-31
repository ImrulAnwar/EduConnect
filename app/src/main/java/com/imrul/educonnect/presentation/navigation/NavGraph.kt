package com.imrul.educonnect.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.imrul.educonnect.core.Routes.Companion.COURSES_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.LOGIN_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.REGISTER_SCREEN_ROUTE
import com.imrul.educonnect.presentation.screen_courses.CoursesScreen
import com.imrul.educonnect.presentation.login.LoginScreen
import com.imrul.educonnect.presentation.login.model.LoginState
import com.imrul.educonnect.presentation.register.RegisterScreen

@Composable
fun NavGraph(
    loginState: LoginState?,
) {
    val navController = rememberNavController()
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
    }
}