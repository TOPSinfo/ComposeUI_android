package com.tops.composeui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


/**
 *  Set Different themes for app in dark and light
 *  mode and save state of theme to material theme
 * */

private val DarkColorPalette = darkColors(
    primary = BrightOrange,
    primaryVariant = MediumOrange,
    secondary = DarkOrange
)

private val LightColorPalette = lightColors(
    primary = BrightOrange,
    primaryVariant = MediumOrange,
    secondary = DarkOrange
)

@Composable
fun AstroYodhaAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}