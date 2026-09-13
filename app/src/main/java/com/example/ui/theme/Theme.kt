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

// 1: Royal Violet
private val RoyalVioletDark = darkColorScheme(
    primary = Color(0xFFCE93D8),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF4A148C),
    onPrimaryContainer = Color(0xFFF3E5F5),
    secondary = Color(0xFFAB47BC),
    onSecondary = Color.White,
    tertiary = Color(0xFFBA68C8),
    background = Color(0xFF12081C),
    onBackground = Color(0xFFF3E5F5),
    surface = Color(0xFF1B0F28),
    onSurface = Color(0xFFF3E5F5),
    surfaceVariant = Color(0xFF28143C),
    outline = Color(0xFF7B1FA2),
    error = Color(0xFFEF5350)
)

private val RoyalVioletLight = lightColorScheme(
    primary = Color(0xFF6A1B9A),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFF3E5F5),
    onPrimaryContainer = Color(0xFF38006B),
    secondary = Color(0xFF8E24AA),
    onSecondary = Color.White,
    tertiary = Color(0xFFAB47BC),
    background = Color(0xFFFAF5FC),
    onBackground = Color(0xFF38006B),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF38006B),
    surfaceVariant = Color(0xFFEDE7F6),
    outline = Color(0xFFD1C4E9),
    error = Color(0xFFD32F2F)
)

// 2: Solar Amber
private val AmberDark = darkColorScheme(
    primary = Color(0xFFFFB300),
    onPrimary = Color.Black,
    primaryContainer = Color(0xFF4E2C00),
    onPrimaryContainer = Color(0xFFFFECB3),
    secondary = Color(0xFFFF7043),
    onSecondary = Color.Black,
    tertiary = Color(0xFFFFCA28),
    background = Color(0xFF140D07),
    onBackground = Color(0xFFFFECB3),
    surface = Color(0xFF22160C),
    onSurface = Color(0xFFFFECB3),
    surfaceVariant = Color(0xFF332112),
    outline = Color(0xFFFFB300),
    error = Color(0xFFEF5350)
)

private val AmberLight = lightColorScheme(
    primary = Color(0xFFC65102),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFE0B2),
    onPrimaryContainer = Color(0xFF3E1A00),
    secondary = Color(0xFFE65100),
    onSecondary = Color.White,
    tertiary = Color(0xFFF57F17),
    background = Color(0xFFFFF8F0),
    onBackground = Color(0xFF3E1A00),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF3E1A00),
    surfaceVariant = Color(0xFFFFECB3),
    outline = Color(0xFFD7CCC8),
    error = Color(0xFFD32F2F)
)

data class AppColors(
    val deepSpace: Color,
    val cosmicPurple: Color,
    val mysticViolet: Color,
    val celestialGold: Color,
    val starlightAmber: Color,
    val nebulaTeal: Color,
    val shadowRose: Color,
    val textColor: Color,
    val textSecondaryColor: Color
)

val LocalAppColors = androidx.compose.runtime.compositionLocalOf {
    AppColors(
        deepSpace = EditorialInk,
        cosmicPurple = EditorialCreamCardDark,
        mysticViolet = EditorialAccentTerracotta,
        celestialGold = EditorialAccentGold,
        starlightAmber = Color(0xFFC792EA),
        nebulaTeal = EditorialAccentOlive,
        shadowRose = EditorialAccentCrimson,
        textColor = Color.White,
        textSecondaryColor = Color.White.copy(alpha = 0.7f)
    )
}

private val CosmicDarkAppColors = AppColors(
    deepSpace = EditorialInk,
    cosmicPurple = CardBackgroundDark,
    mysticViolet = EditorialAccentTerracotta,
    celestialGold = EditorialAccentGold,
    starlightAmber = Color(0xFFC792EA),
    nebulaTeal = EditorialAccentOlive,
    shadowRose = EditorialAccentCrimson,
    textColor = Color.White,
    textSecondaryColor = Color.White.copy(alpha = 0.7f)
)

private val CosmicLightAppColors = AppColors(
    deepSpace = EditorialParchment,
    cosmicPurple = CardBackgroundDark,
    mysticViolet = EditorialAccentTerracotta,
    celestialGold = EditorialAccentGold,
    starlightAmber = Color(0xFFC792EA),
    nebulaTeal = EditorialAccentOlive,
    shadowRose = EditorialAccentCrimson,
    textColor = Color.White,
    textSecondaryColor = Color.White.copy(alpha = 0.7f)
)

private val RoyalVioletDarkAppColors = AppColors(
    deepSpace = Color(0xFF12081C),
    cosmicPurple = Color(0xFF1B0F28),
    mysticViolet = Color(0xFF6A1B9A),
    celestialGold = Color(0xFFCE93D8),
    starlightAmber = Color(0xFFAB47BC),
    nebulaTeal = Color(0xFFBA68C8),
    shadowRose = Color(0xFFEF5350),
    textColor = Color.White,
    textSecondaryColor = Color.White.copy(alpha = 0.7f)
)

private val RoyalVioletLightAppColors = AppColors(
    deepSpace = Color(0xFFFAF5FC),
    cosmicPurple = Color(0xFF1B0F28),
    mysticViolet = Color(0xFF6A1B9A),
    celestialGold = Color(0xFFCE93D8),
    starlightAmber = Color(0xFFAB47BC),
    nebulaTeal = Color(0xFFBA68C8),
    shadowRose = Color(0xFFEF5350),
    textColor = Color.White,
    textSecondaryColor = Color.White.copy(alpha = 0.7f)
)

private val AmberDarkAppColors = AppColors(
    deepSpace = Color(0xFF140D07),
    cosmicPurple = Color(0xFF22160C),
    mysticViolet = Color(0xFFC65102),
    celestialGold = Color(0xFFFFB300),
    starlightAmber = Color(0xFFFF7043),
    nebulaTeal = Color(0xFFFFCA28),
    shadowRose = Color(0xFFEF5350),
    textColor = Color.White,
    textSecondaryColor = Color.White.copy(alpha = 0.8f)
)

private val AmberLightAppColors = AppColors(
    deepSpace = Color(0xFFFFF8F0),
    cosmicPurple = Color(0xFF22160C),
    mysticViolet = Color(0xFFC65102),
    celestialGold = Color(0xFFC65102),
    starlightAmber = Color(0xFFE65100),
    nebulaTeal = Color(0xFFF57F17),
    shadowRose = Color(0xFFD32F2F),
    textColor = Color(0xFF3E1A00),
    textSecondaryColor = Color(0xFF3E1A00).copy(alpha = 0.8f)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    colorSchemeIndex: Int = 0,
    content: @Composable () -> Unit
) {
    val colorScheme = when (colorSchemeIndex) {
        1 -> if (darkTheme) RoyalVioletDark else RoyalVioletLight
        2 -> if (darkTheme) AmberDark else AmberLight
        else -> if (darkTheme) CosmicDark else CosmicLight
    }

    val appColors = when (colorSchemeIndex) {
        1 -> if (darkTheme) RoyalVioletDarkAppColors else RoyalVioletLightAppColors
        2 -> if (darkTheme) AmberDarkAppColors else AmberLightAppColors
        else -> if (darkTheme) CosmicDarkAppColors else CosmicLightAppColors
    }

    androidx.compose.runtime.CompositionLocalProvider(LocalAppColors provides appColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}

