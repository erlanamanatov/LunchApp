package com.example.lunchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.lunchapp.ui.screens.mainscreen.MainScreen
import com.example.lunchapp.ui.screens.restaurant_screen.RestaurantScreen
import com.example.lunchapp.ui.theme.AppColors
import com.example.lunchapp.ui.theme.LunchAppTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
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

