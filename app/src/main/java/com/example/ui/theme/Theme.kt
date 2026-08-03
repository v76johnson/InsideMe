package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = EditorialAccentTerracotta,
    onPrimary = Color.White,
    primaryContainer = EditorialCreamCardDark,
    onPrimaryContainer = EditorialTextPrimaryDark,
    secondary = EditorialAccentGold,
    onSecondary = EditorialInk,
    tertiary = EditorialAccentOlive,
    onTertiary = Color.White,
    background = EditorialInk,
    onBackground = EditorialTextPrimaryDark,
    surface = CardBackgroundDark,
    onSurface = EditorialTextPrimaryDark,
    surfaceVariant = EditorialInkLight,
    onSurfaceVariant = EditorialTextSecondaryDark,
    outline = EditorialBorderDark,
    error = EditorialAccentCrimson
)

private val LightColorScheme = lightColorScheme(
    primary = EditorialAccentTerracotta,
    onPrimary = Color.White,
    primaryContainer = EditorialParchmentDark,
    onPrimaryContainer = EditorialTextPrimaryLight,
    secondary = EditorialAccentGold,
    onSecondary = Color.White,
    tertiary = EditorialAccentOlive,
    onTertiary = Color.White,
    background = EditorialParchment,
    onBackground = EditorialTextPrimaryLight,
    surface = EditorialCreamCard,
    onSurface = EditorialTextPrimaryLight,
    surfaceVariant = EditorialParchmentDark,
    onSurfaceVariant = EditorialTextSecondaryLight,
    outline = EditorialBorderLight,
    error = EditorialAccentCrimson
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true, // Default to dark cosmic aesthetic
    dynamicColor: Boolean = false, // Preserve brand identity
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
