package com.aneke.peter.kwg.ui.theme

import android.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = CompanyBlue,
    primaryVariant = Purple700,
    secondary = Teal200,
    onBackground = CompanyBlue
)

private val LightColorPalette = lightColors(
    primary = CompanyBlue,
    primaryVariant = CompanyBlue,
    secondary = Teal200,
    onBackground = White,
    background = CompanyBlue


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)


@Composable
fun KWGTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }
    val colors = LightColorPalette

        MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}