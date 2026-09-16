package com.gtavicountdown.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.gtavicountdown.app.ui.home.HomeScreen
import com.gtavicountdown.app.ui.theme.GtaViCountdownTheme

/**
 * Ponto de entrada do app.
 *
 * Em um app 100% Compose, a Activity tem uma responsabilidade mínima:
 * apenas chamar setContent { } e delegar tudo para os Composables.
 * Não há XML de layout, não há findViewById — a UI inteira é descrita
 * em Kotlin dentro de HomeScreen().
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // v0.2: tema próprio com paleta neon. Escuro por padrão
            // (segue o sistema via isSystemInDarkTheme).
            GtaViCountdownTheme {
                Surface {
                    HomeScreen()
                }
            }
        }
    }
}
