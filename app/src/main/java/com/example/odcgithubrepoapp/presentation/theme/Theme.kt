package com.example.odcgithubrepoapp.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = ThemeColors.Day.primary,
    onPrimary = ThemeColors.Day.text,
    secondary = ThemeColors.Day.secondary,
    error = light_error,
    onError = light_onError,
    background = ThemeColors.Day.background,
    surface = ThemeColors.Day.surface,

)

private val DarkColorScheme = darkColorScheme(
    primary = ThemeColors.Night.primary,
    onPrimary = ThemeColors.Night.text,
    secondary = ThemeColors.Night.secondary,
    error = light_error,
    onError = light_onError,
    background = ThemeColors.Night.background,
    surface = ThemeColors.Night.surface,
)

@Composable
fun ODCGithubRepoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val statusBar = ThemeColors.Day.accent

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = statusBar.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = shapes,
        content = content
    )
}