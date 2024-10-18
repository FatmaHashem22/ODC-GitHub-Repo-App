package com.example.odcgithubrepoapp.presentation.theme

import android.provider.CalendarContract
import androidx.compose.ui.graphics.Color


val light_primary = Color(0xFFF7F7F7)
val light_onPrimary = Color(0xFF000000)
val light_secondary = Color(0xFF929292)
val light_onSecondary = Color(0xFFFFFFFF)

val light_error = Color(0xFFBA1A1A)
val light_onError = Color(0xFFFFFFFF)

val light_background = Color(0xFF929292)
val light_onBackground = Color(0xFF191C1C)

val light_surface = Color(0xFFC0C0C0)
val light_onSurface = Color(0xFF000000)
val dark_surface = Color(0xFF3F3F3F)

val LightGreen = Color(0xFF31B057)
val LightGray = Color(0xFF929292)

val dark_background = Color(0xFF121212)
val dark_primary = Color(0xFF212121)
val dark_secondary = Color(0xFF38323B)
val dark_purple = Color(0xFF9C27B0)
val light_purple = Color(0xFF9B59B6)

val accent = Color(0xFF7A288A)


sealed class ThemeColors(
    val background : Color,
    val surface : Color,
    val primary : Color,
    val secondary : Color,
    val text : Color,
    val appBar : Color,
    val accent : Color,
    val button : Color
) {
    object Night : ThemeColors (
        background = dark_background,
        surface = dark_surface,
        primary = dark_primary,
        secondary = dark_secondary,
        text = Color.White,
        appBar = dark_purple,
        accent = accent,
        button = dark_purple

    )
    object Day : ThemeColors(
        background = light_background,
        surface = light_surface,
        primary = light_primary,
        secondary = light_secondary,
        text = dark_secondary,
        appBar = dark_purple,
        accent = accent,
        button = dark_purple
    )
}