package com.example.lunchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.material.ExperimentalMaterialApi
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
//                MainScreen(Modifier.fillMaxSize())
                RestaurantScreen()
            }
        }
    }
}

