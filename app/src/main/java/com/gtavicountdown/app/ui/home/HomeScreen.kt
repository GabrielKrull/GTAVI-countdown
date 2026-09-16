package com.gtavicountdown.app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gtavicountdown.app.R
import com.gtavicountdown.app.ui.theme.GtaColors

/**
 * Home v0.3: background na paleta exata da logo, 2 camadas planas.
 *
 * - Base #1C0030 predominante (topo puro para a logo continuar legível),
 *   descendo suavemente até #3C0055 só no rodapé.
 * - Um único véu vertical na ordem da logo: azul -> roxo -> magenta
 *   -> rosa -> coral -> laranja, tudo em alphas baixos (<=14%).
 * - Sem círculos, partículas, ondas, formas, glow ou gradientes saturados.
 * - Logo, cards, contador, datas e lógica: intocados.
 */
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val preload by viewModel.preloadCountdown.collectAsState()
    val release by viewModel.releaseCountdown.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                // Camada 1/2 (base): #1C0030 puro no topo (logo legível),
                // segura o escuro até quase metade da tela e só então
                // desce suavemente até #3C0055 no rodapé.
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to GtaColors.WashDeepMagenta,
                        0.45f to GtaColors.WashMagenta,
                        0.75f to Color(0xFFF6658F),
                        1.0f to GtaColors.WashCoral
                    )
                )
            )
            .background(
                // Camada 2/2 (véu único): a transição da logo de cima
                // para baixo — azul/roxo no topo, magenta/rosa no meio,
                // coral/laranja só no rodapé. Alphas baixos: cor que se
                // sente, não que se vê. Maior parte da tela segue escura.
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.0f to GtaColors.WashBlue.copy(alpha = 0.14f),
                        0.18f to GtaColors.WashViolet.copy(alpha = 0.10f),
                        0.32f to Color.Transparent,
                        0.55f to GtaColors.WashDeepMagenta.copy(alpha = 0.10f),
                        0.70f to GtaColors.WashMagenta.copy(alpha = 0.07f),
                        0.82f to GtaColors.WashRose.copy(alpha = 0.05f),
                        0.91f to GtaColors.WashCoral.copy(alpha = 0.05f),
                        1.0f to GtaColors.WashAmber.copy(alpha = 0.04f)
                    )
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Respiro acima da logo: encosta em nada, sem exagero.
        Spacer(Modifier.height(28.dp))

        // Logo como cabeçalho: ~58% da largura útil (faixa 50-65%),
        // proporção original preservada (Fit), centralizada pelo Column.
        // fillMaxWidth(fraction) é responsivo — adapta a qualquer
        // largura de tela sem pixel fixo.
        Image(
            painter = painterResource(id = R.drawable.gta_vi_logo),
            contentDescription = "Logo de Grand Theft Auto VI",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth(0.58f)
                .heightIn(max = 220.dp)
        )

        Spacer(Modifier.height(14.dp))

        Text(
            text = "COUNTDOWN",
            color = GtaColors.OnDarkSecondary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 8.sp
        )

        Spacer(Modifier.height(10.dp))

        // Único acento de cor da tela: filete pôr-do-sol nas cores da logo.
        Box(
            modifier = Modifier
                .width(56.dp)
                .height(3.dp)
                .background(
                    Brush.horizontalGradient(
                        listOf(GtaColors.SunsetPink, GtaColors.SunsetOrange)
                    ),
                    RoundedCornerShape(50)
                )
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "\"A espera está acabando.\"",
            color = GtaColors.OnDarkSecondary,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(24.dp))

        CountdownCard(
            title = "DOWNLOAD",
            subtitle = "Pré-carregamento • 12/11/2026",
            state = preload,
            finishedMessage = "DOWNLOAD DISPONÍVEL!"
        )

        Spacer(Modifier.height(12.dp))

        CountdownCard(
            title = "LANÇAMENTO",
            subtitle = "Lançamento oficial • 19/11/2026",
            state = release,
            finishedMessage = "GTA VI FOI LANÇADO! 🎉"
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = "APP DE FÃ • NÃO OFICIAL • NÃO AFILIADO À ROCKSTAR GAMES\nData de download é uma estimativa configurável.",
            color = GtaColors.OnDarkSecondary.copy(alpha = 0.7f),
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )

        Spacer(Modifier.height(20.dp))
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
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, GtaColors.Outline, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = GtaColors.Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Black,
                fontSize = 17.sp,
                letterSpacing = 3.sp
            )
            Spacer(Modifier.height(2.dp))
            Text(subtitle, color = GtaColors.OnDarkSecondary, fontSize = 12.sp)

            Spacer(Modifier.height(14.dp))

            if (state.isFinished) {
                Text(
                    text = finishedMessage,
                    color = GtaColors.SunsetOrange,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            } else {
                // Linha centralizada como bloco; cada unidade tem largura
                // fixa (item 7) para o dígito nunca "puxar" o vizinho.
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TimeUnit(state.days, "DIAS")
                    Spacer(Modifier.width(8.dp))
                    TimeUnit(state.hours, "HORAS")
                    Spacer(Modifier.width(8.dp))
                    TimeUnit(state.minutes, "MIN")
                    Spacer(Modifier.width(8.dp))
                    TimeUnit(state.seconds, "SEG")
                }
            }
        }
    }
}

@Composable
private fun TimeUnit(value: Long, label: String) {
    // Largura fixa por unidade: número e rótulo sempre alinhados ao
    // centro da mesma coluna, independente de 1, 2 ou 3 dígitos.
    // Sem rotação/skew — só alinhamento (item 8).
    Column(
        modifier = Modifier.width(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value.toString().padStart(2, '0'),
            color = Color.White,
            fontSize = 30.sp,
            fontWeight = FontWeight.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(2.dp))
        Text(
            text = label,
            color = GtaColors.OnDarkSecondary,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
