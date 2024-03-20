package com.imrul.educonnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.imrul.educonnect.presentation.register.RegisterScreen
import com.imrul.educonnect.ui.theme.EduConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EduConnectTheme {

                RegisterScreen()
            }
        }
    }
}