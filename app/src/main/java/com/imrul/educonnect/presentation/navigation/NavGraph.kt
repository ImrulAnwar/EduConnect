package com.imrul.educonnect.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.imrul.educonnect.core.Routes.Companion.HOME_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.LOGIN_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.REGISTER_SCREEN_ROUTE
import com.imrul.educonnect.presentation.home.HomeScreen
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
        startDestination = if (loginState?.user != null) HOME_SCREEN_ROUTE else LOGIN_SCREEN_ROUTE
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
            route = HOME_SCREEN_ROUTE
        ) {
            HomeScreen(navController = navController)
        }
    }
}