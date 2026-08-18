package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Editorial Aesthetic Palette - Warm Parchment & Deep Obsidian Ink
val EditorialParchment = Color(0xFFFAF7F2)
val EditorialParchmentDark = Color(0xFFF3ECE0)
val EditorialInk = Color(0xFF161518)
val EditorialInkLight = Color(0xFF222026)

val EditorialCreamCard = Color(0xFFFFFFFF)
val EditorialCreamCardDark = Color(0xFF1E1D22)

val EditorialAccentTerracotta = Color(0xFFB388FF) // Light Purple
val EditorialAccentGold = Color(0xFFD49B2A)
val EditorialAccentCrimson = Color(0xFFA83232)
val EditorialAccentOlive = Color(0xFF5E7854)
val EditorialAccentNavy = Color(0xFF2E4057)

val EditorialBorderLight = Color(0xFFE4DDD3)
val EditorialBorderDark = Color(0xFF33303B)

val EditorialTextPrimaryLight = Color(0xFF161518)
val EditorialTextSecondaryLight = Color(0xFF6B6570)
val EditorialTextPrimaryDark = Color(0xFFF7F4EF)
val EditorialTextSecondaryDark = Color(0xFFAA29C8)

// Mapped Palette (Dynamic based on active theme via LocalAppColors)
val DeepSpace: Color
    @Composable get() = LocalAppColors.current.deepSpace

val CosmicPurple: Color
    @Composable get() = LocalAppColors.current.cosmicPurple

val MysticViolet: Color
    @Composable get() = LocalAppColors.current.mysticViolet

val CelestialGold: Color
    @Composable get() = LocalAppColors.current.celestialGold

val StarlightAmber: Color
    @Composable get() = LocalAppColors.current.starlightAmber

val NebulaTeal: Color
    @Composable get() = LocalAppColors.current.nebulaTeal

val ShadowRose: Color
    @Composable get() = LocalAppColors.current.shadowRose

val CardBackgroundDark = Color(0xFF201E25)
val CardBorderDark = EditorialBorderDark
val TextPrimaryDark = EditorialTextPrimaryDark
val TextSecondaryDark = EditorialTextSecondaryDark

val CosmicLightBg = EditorialParchment
val CardBackgroundLight = EditorialCreamCard
val PrimaryLight = EditorialAccentTerracotta
val SecondaryLight = EditorialAccentNavy
val GoldLight = EditorialAccentGold

