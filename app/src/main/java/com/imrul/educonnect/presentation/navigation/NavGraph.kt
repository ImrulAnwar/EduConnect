package com.imrul.educonnect.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.imrul.educonnect.core.Constants.Companion.RECEIVER_UID_VARIABLE_NAME
import com.imrul.educonnect.core.Routes.Companion.COURSES_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.EDIT_PROFILE_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.LOGIN_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.MESSAGES_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.PROFILE_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.REGISTER_SCREEN_ROUTE
import com.imrul.educonnect.core.Routes.Companion.SEND_MESSAGE_SCREEN_ROUTE
import com.imrul.educonnect.presentation.screen_courses.CoursesScreen
import com.imrul.educonnect.presentation.screen_edit_profile.EditProfileScreen
import com.imrul.educonnect.presentation.screen_login.LoginScreen
import com.imrul.educonnect.presentation.screen_login.model.LoginState
import com.imrul.educonnect.presentation.screen_register.RegisterScreen
import com.imrul.educonnect.presentation.screen_messages.MessagesScreen
import com.imrul.educonnect.presentation.screen_profile.ProfileScreen
import com.imrul.educonnect.presentation.screen_send_message.SendMessageScreen

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
        composable(
            route = EDIT_PROFILE_SCREEN_ROUTE
        ) {
            EditProfileScreen(navController = navController)
        }
        composable(
            route = SEND_MESSAGE_SCREEN_ROUTE + "/{${RECEIVER_UID_VARIABLE_NAME}}",
            arguments = listOf(
                navArgument(name = RECEIVER_UID_VARIABLE_NAME) {
                    type = NavType.StringType
                }
            )
        ) {
            SendMessageScreen(
                navController = navController,
                receiverUid = it.arguments?.getString(RECEIVER_UID_VARIABLE_NAME)
            )
        }
    }
}