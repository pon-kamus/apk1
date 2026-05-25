package com.example.hiraganapractice.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = DarkGray,
    onPrimary = Color.White,
    secondary = BluePrimary,
    background = GrayBackground,
    surface = Color.White,
    onSurface = DarkGray,
    onBackground = DarkGray
)

@Composable
fun HiraganaPracticeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
