package com.example.lunchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.lunchapp.ui.screens.mainscreen.MainScreen
import com.example.lunchapp.ui.theme.LunchAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LunchAppTheme {
                MainScreen(Modifier.fillMaxSize())
            }
        }
    }
}

