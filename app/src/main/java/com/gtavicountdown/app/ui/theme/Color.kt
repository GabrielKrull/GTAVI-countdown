package com.gtavicountdown.app.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Paleta extraída DIRETAMENTE da logo (v0.3).
 *
 * Base predominante: #1C0030, com transições suaves usando os roxos e
 * azuis da logo e apenas pontos de magenta/rosa/coral/laranja.
 * Regra: nada de neon, glow, formas ou partículas — o background é
 * uma superfície colorida escura em 2 camadas planas full-screen.
 */
object GtaColors {
    // Base predominante + secundária para a profundidade (topo -> base).
    val Background = Color(0xFF1C0030)
    val BackgroundTop = Color(0xFF1C0030)
    val BackgroundBottom = Color(0xFF3C0055)

    // Transições frias (topo/meio): azul e azul/roxo da parte superior da logo.
    val WashBlue = Color(0xFF455ED5)
    val WashViolet = Color(0xFF7359CE)

    // Transições quentes (meio/inferior): do roxo/magenta escuro ao âmbar.
    val WashDeepMagenta = Color(0xFF670368)
    val WashMagenta = Color(0xFFE44DBC)
    val WashRose = Color(0xFFF6658F)
    val WashCoral = Color(0xFFEF7B6D)
    val WashAmber = Color(0xFFEA8D50)

    // Cards: tom escuro neutro-quente, um degrau de luminosidade acima
    // da base roxa — separação por valor, sem borda neon.
    val Surface = Color(0xFF17151C)
    val Outline = Color(0xFF342F42)

    // Texto.
    val OnDark = Color(0xFFFFFFFF)
    val OnDarkSecondary = Color(0xFFB9B7C0)

    // Acentos pontuais — 100% paleta da logo (sem interpretação neon).
    // Filete e "zerou": rosa -> laranja da logo. Tertiary: azul da logo.
    val SunsetPink = Color(0xFFF6658F)
    val SunsetOrange = Color(0xFFEA8D50)
    val PalmBlue = Color(0xFF455ED5)
    val DeepPurple = Color(0xFF3C0055)
}
