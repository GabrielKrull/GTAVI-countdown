package com.gtavicountdown.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * Tela inicial do app.
 *
 * Nesta v0.1, as cores estão fixas aqui mesmo (Color(0xFF...)) só para
 * validarmos rápido que o contador funciona. Na v0.2 vamos extrair essa
 * paleta para um MaterialTheme próprio (Color.kt / Theme.kt / Type.kt),
 * que é o jeito certo de fazer isso em um projeto real — evita repetir
 * cores "mágicas" em vários arquivos.
 */
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    // collectAsState() transforma o StateFlow do ViewModel em um estado
    // que o Compose observa. Sempre que preloadCountdown ou release
    // Countdown mudam (a cada segundo), só as partes da tela que usam
    // esses valores são redesenhadas — não a tela inteira.
    val preload by viewModel.preloadCountdown.collectAsState()
    val release by viewModel.releaseCountdown.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1A0B2E), Color(0xFF0D0518))
                )
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(32.dp))

        Text(
            text = "GTA VI",
            color = Color(0xFFFF3D7F),
            fontSize = 40.sp,
            fontWeight = FontWeight.Black
        )
        Text(
            text = "COUNTDOWN",
            color = Color(0xFFFFA53D),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "\"A espera está acabando.\"",
            color = Color(0xFFB39DDB),
            fontSize = 14.sp
        )

        Spacer(Modifier.height(40.dp))

        CountdownCard(
            title = "DOWNLOAD",
            subtitle = "Liberação do pré-carregamento",
            state = preload,
            finishedMessage = "DOWNLOAD DISPONÍVEL!"
        )

        Spacer(Modifier.height(24.dp))

        CountdownCard(
            title = "LANÇAMENTO",
            subtitle = "Lançamento oficial",
            state = release,
            finishedMessage = "GTA VI FOI LANÇADO! \uD83C\uDF89"
        )
    }
}

@Composable
private fun CountdownCard(
    title: String,
    subtitle: String,
    state: CountdownState,
    finishedMessage: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF241436))
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(subtitle, color = Color(0xFFB39DDB), fontSize = 12.sp)

            Spacer(Modifier.height(16.dp))

            if (state.isFinished) {
                Text(
                    text = finishedMessage,
                    color = Color(0xFFFFA53D),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            } else {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    TimeUnit(state.days, "DIAS")
                    TimeUnit(state.hours, "HORAS")
                    TimeUnit(state.minutes, "MIN")
                    TimeUnit(state.seconds, "SEG")
                }
            }
        }
    }
}

@Composable
private fun TimeUnit(value: Long, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value.toString().padStart(2, '0'),
            color = Color(0xFFFF3D7F),
            fontSize = 26.sp,
            fontWeight = FontWeight.Black
        )
        Text(text = label, color = Color(0xFFB39DDB), fontSize = 10.sp)
    }
}
