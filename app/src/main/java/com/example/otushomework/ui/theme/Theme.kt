package com.example.otushomework.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Yellow800,
    secondary = Teal200,
    secondaryVariant = Red800
)

private val LightColorPalette = lightColors(
    primary = Yellow500,
    primaryVariant = Yellow700,
    secondary = Red500,
    secondaryVariant = Red700
)

@Composable
fun OtusHomeworkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}