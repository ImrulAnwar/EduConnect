package com.imrul.educonnect

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.imrul.educonnect.presentation.components.CustomText
import com.imrul.educonnect.presentation.screen_login.LoginViewModel
import com.imrul.educonnect.presentation.navigation.BottomBarScreens
import com.imrul.educonnect.presentation.navigation.NavGraph
import com.imrul.educonnect.ui.theme.EduConnectTheme
import com.imrul.educonnect.ui.theme.Maroon10
import com.imrul.educonnect.ui.theme.Maroon20
import com.imrul.educonnect.ui.theme.Maroon70
import com.imrul.educonnect.ui.theme.poppinsFontFamily
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.currentUser()
        setContent {
            val loginState by viewModel.loginState.collectAsState()


            EduConnectTheme {
                val screens = listOf(
                    BottomBarScreens.CoursesScreenObject,
                    BottomBarScreens.MessagesScreenObject,
                    BottomBarScreens.ProfileScreenObject
                )
                val selectedItemIndex by viewModel.selectedItemIndex.collectAsState()
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val shouldShowBottomNavigation = when (navBackStackEntry?.destination?.route) {
                    screens[0].title,
                    screens[1].title,
                    screens[2].title,
                    -> true

                    else -> false
                }

                LaunchedEffect(loginState, selectedItemIndex) {
                    viewModel.currentUser()
                }

                Scaffold(
                    bottomBar = {
                        if (shouldShowBottomNavigation)
                            NavigationBar(
                                containerColor = Maroon10
                            ) {
                                screens.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            viewModel.setSelectedItem(index)
                                            navController.popBackStack()
                                            navController.navigate(item.title)
                                        },
                                        label = {
                                            CustomText(
                                                text = item.title,
                                                size = 10.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        },
                                        alwaysShowLabel = false,
                                        icon = {
                                            BadgedBox(
                                                badge = {
                                                    if (item.badgeCount != null)
                                                        Badge { Text(text = item.badgeCount.toString()) }
                                                    else if (item.hasNews)
                                                        Badge()
                                                }
                                            ) {
                                                Icon(
                                                    imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                                    contentDescription = item.title,
                                                    tint = if (index == selectedItemIndex) Maroon70 else Maroon20 // Set the desired color conditionally
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                    },
                    containerColor = Maroon10,
                    contentColor = Maroon70
                ) {
                    NavGraph(loginState, navController)
                }
            }
        }
    }
}