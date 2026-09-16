package com.gtavicountdown.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

/**
 * Tema v0.3: sempre escuro, sem variante clara.
 *
 * Decisão: você pediu "GTA VI vibes" sem foco em claro/escuro.
 * Manter um único esquema editorial escuro evita telas claras que
 * quebrariam a identidade do pôster. Cores 100% da paleta da logo.
 * O parâmetro darkTheme foi removido de propósito.
 */
private val Scheme = darkColorScheme(
    primary = GtaColors.SunsetPink,
    secondary = GtaColors.SunsetOrange,
    tertiary = GtaColors.PalmBlue,
    background = GtaColors.Background,
    surface = GtaColors.Surface,
    outline = GtaColors.Outline,
    onBackground = GtaColors.OnDark,
    onSurface = GtaColors.OnDark
)

@Composable
fun GtaViCountdownTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = Scheme,
        content = content
    )
}
