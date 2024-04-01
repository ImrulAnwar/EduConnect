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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.imrul.educonnect.presentation.login.LoginViewModel
import com.imrul.educonnect.presentation.login.model.LoginState
import com.imrul.educonnect.presentation.navigation.BottomBarScreens
import com.imrul.educonnect.presentation.navigation.NavGraph
import com.imrul.educonnect.ui.theme.EduConnectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private var loginState: LoginState? = null

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.currentUser()
        setContent {
            val loginState by viewModel.loginState.collectAsState()
            LaunchedEffect(loginState) {
                viewModel.currentUser()
            }

            EduConnectTheme {
                val screens = listOf(
                    BottomBarScreens.CoursesScreenObject,
                    BottomBarScreens.MessagesScreenObject,
                    BottomBarScreens.ProfileScreenObject
                )
                var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val shouldShowBottomNavigation = when (navBackStackEntry?.destination?.route) {
                    screens[0].title,
                    screens[1].title,
                    screens[2].title,
                    -> true
                    else -> false
                }

                Scaffold(
                    bottomBar = {
                        if (shouldShowBottomNavigation && loginState.user != null)
                            NavigationBar {
                                screens.forEachIndexed { index, item ->
                                    NavigationBarItem(
                                        selected = selectedItemIndex == index,
                                        onClick = {
                                            selectedItemIndex = index
                                            navController.navigate(item.title)
                                        },
                                        label = { Text(text = item.title) },
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
                                                    contentDescription = item.title
                                                )
                                            }
                                        }
                                    )
                                }
                            }
                    }
                ) {
                    NavGraph(loginState, navController)
                }
            }
        }
    }
}