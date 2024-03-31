package com.imrul.educonnect.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.imrul.educonnect.core.Routes

sealed class BottomBarScreens(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
) {
    data object CoursesScreenObject : BottomBarScreens(
        title = Routes.COURSES_SCREEN_ROUTE,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false,
    )

    data object MessagesScreenObject : BottomBarScreens(
        title = Routes.MESSAGES_SCREEN_ROUTE,
        selectedIcon = Icons.Filled.Email,
        unselectedIcon = Icons.Outlined.Email,
        hasNews = false,
        badgeCount = 45
    )

    data object ProfileScreenObject : BottomBarScreens(
        title = Routes.PROFILE_SCREEN_ROUTE,
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasNews = true,
    )
}