package com.jedev.tempnumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jedev.tempnumber.ui.screens.numberRedirect.NumberRedirectScreen
import com.jedev.tempnumber.ui.screens.settings.SettingsScreen
import com.jedev.tempnumber.ui.theme.WhatsNumberTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            WhatsNumberTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Routers.numberTemp) {
                        composable(Routers.numberTemp) { NumberRedirectScreen(navController) }
                        composable(Routers.settings) { SettingsScreen(navController) }
                    }
                }
            }
        }
    }
}
