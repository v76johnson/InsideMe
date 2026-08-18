package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// 0: Cosmic Velvet (Default)
private val CosmicDark = darkColorScheme(
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

private val CosmicLight = lightColorScheme(
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

// 1: Nebula Emerald
private val EmeraldDark = darkColorScheme(
    primary = Color(0xFF81C784),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF163832),
    onPrimaryContainer = Color(0xFFE8F5E9),
    secondary = Color(0xFF4DB6AC),
    onSecondary = Color.Black,
    tertiary = Color(0xFFA5D6A7),
    background = Color(0xFF0F201C),
    onBackground = Color(0xFFE8F5E9),
    surface = Color(0xFF152A25),
    onSurface = Color(0xFFE8F5E9),
    surfaceVariant = Color(0xFF1D3B34),
    outline = Color(0xFF2E7D6A),
    error = Color(0xFFEF5350)
)

private val EmeraldLight = lightColorScheme(
    primary = Color(0xFF2E7D6A),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE0F2F1),
    onPrimaryContainer = Color(0xFF004D40),
    secondary = Color(0xFF00897B),
    onSecondary = Color.White,
    tertiary = Color(0xFF43A047),
    background = Color(0xFFF1F8F6),
    onBackground = Color(0xFF00251A),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF00251A),
    surfaceVariant = Color(0xFFE0F2F1),
    outline = Color(0xFF80CBC4),
    error = Color(0xFFD32F2F)
)

// 2: Solar Amber
private val AmberDark = darkColorScheme(
    primary = Color(0xFFF39C12),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF3E2723),
    onPrimaryContainer = Color(0xFFFFE0B2),
    secondary = Color(0xFFD76D38),
    onSecondary = Color.White,
    tertiary = Color(0xFFFFB74D),
    background = Color(0xFF1A120B),
    onBackground = Color(0xFFFFE0B2),
    surface = Color(0xFF261C14),
    onSurface = Color(0xFFFFE0B2),
    surfaceVariant = Color(0xFF33231A),
    outline = Color(0xFF8D6E63),
    error = Color(0xFFE53935)
)

private val AmberLight = lightColorScheme(
    primary = Color(0xFFD35400),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFDEBD0),
    onPrimaryContainer = Color(0xFF6E2C00),
    secondary = Color(0xFFE67E22),
    onSecondary = Color.White,
    tertiary = Color(0xFFD4AC0D),
    background = Color(0xFFFBFAF8),
    onBackground = Color(0xFF4A2306),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF4A2306),
    surfaceVariant = Color(0xFFFAD7A0),
    outline = Color(0xFFEDBB99),
    error = Color(0xFFC0392B)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    colorSchemeIndex: Int = 0,
    content: @Composable () -> Unit
) {
    val colorScheme = when (colorSchemeIndex) {
        1 -> if (darkTheme) EmeraldDark else EmeraldLight
        2 -> if (darkTheme) AmberDark else AmberLight
        else -> if (darkTheme) CosmicDark else CosmicLight
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

