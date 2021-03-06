package com.example.lunchapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Color(0xFFfafafa),
    primaryVariant = Purple700,
    secondary = Color(0xFFf84470),
    background = Color(0xFFf5f5f5),
    surface = Color(0xFFfcfcfc),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

object AppColors {
    val background = Color(0xFFf5f5f5)

}

@Composable
fun LunchAppTheme(content: @Composable() () -> Unit) {
    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}