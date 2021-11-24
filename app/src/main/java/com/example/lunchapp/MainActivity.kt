package com.example.lunchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lunchapp.ui.screens.Screen
import com.example.lunchapp.ui.screens.mainscreen.MainScreen
import com.example.lunchapp.ui.screens.restaurant_screen.RestaurantScreen
import com.example.lunchapp.ui.theme.LunchAppTheme

@ExperimentalTransitionApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LunchAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
                    composable(Screen.MainScreen.route) {
                        MainScreen(Modifier.fillMaxSize()) {
                            navController.navigate(Screen.RestaurantScreen.route)
                        }
                    }
                    composable(Screen.RestaurantScreen.route) {
                        RestaurantScreen() {
                            navController.navigateUp()
                        }
                    }
                }
            }
        }
    }
}

