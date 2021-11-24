package com.example.lunchapp.ui.screens

sealed class Screen(val route: String, val title: String) {
    object MainScreen : Screen("profile", "Main Screen")
    object RestaurantScreen : Screen("friendslist", "Restaurant Screen")
}