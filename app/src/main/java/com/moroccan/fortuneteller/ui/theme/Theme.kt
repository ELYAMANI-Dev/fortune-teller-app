package com.moroccan.fortuneteller.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary           = BrandPrimary,
    onPrimary         = BrandWhite,
    primaryContainer  = BrandPrimaryDark,
    background        = BrandBackground,
    surface           = BrandSurface,
    surfaceVariant    = BrandSurfaceAlt,
    onBackground      = BrandWhite,
    onSurface         = BrandWhite,
    onSurfaceVariant  = BrandGray,
    secondary         = BrandGold,
    onSecondary       = BrandWhite
)

@Composable
fun FortuneTellerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography  = Typography,
        content     = content
    )
}
