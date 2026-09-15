package com.gtavicountdown.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtavicountdown.app.config.CountdownConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant

/**
 * ViewModel da tela inicial.
 *
 * Por que um ViewModel e não calcular tudo direto no Composable?
 * O ViewModel sobrevive a mudanças de configuração (ex: girar a tela,
 * mudar o idioma) sem perder estado, e mantém a lógica de cálculo
 * separada da lógica de desenho da tela. O Composable só observa
 * o que o ViewModel expõe (StateFlow) — ele não sabe COMO o tempo
 * é calculado, só sabe EXIBIR o resultado.
 */
class HomeViewModel : ViewModel() {

    private val _preloadCountdown =
        MutableStateFlow(calculateCountdown(CountdownConfig.PRELOAD_DATE))
    val preloadCountdown: StateFlow<CountdownState> = _preloadCountdown.asStateFlow()

    private val _releaseCountdown =
        MutableStateFlow(calculateCountdown(CountdownConfig.RELEASE_DATE))
    val releaseCountdown: StateFlow<CountdownState> = _releaseCountdown.asStateFlow()

    init {
        startTicking()
    }

    /**
     * Atualiza os dois contadores a cada segundo.
     *
     * Ponto importante: a cada "tick" recalculamos a diferença entre
     * agora e a data-alvo a partir do zero — NUNCA incrementamos um
     * contador manualmente. É isso que garante que o valor esteja
     * sempre correto mesmo se o app ficar horas em segundo plano ou
     * se o celular for reiniciado: o cálculo sempre parte do relógio
     * real do sistema, não de um número acumulado que poderia
     * "perder o passo".
     *
     * Essa coroutine roda dentro de viewModelScope, que é cancelada
     * automaticamente quando o ViewModel é destruído — não fica um
     * timer "fantasma" rodando e gastando bateria.
     */
    private fun startTicking() {
        viewModelScope.launch {
            while (true) {
                _preloadCountdown.value = calculateCountdown(CountdownConfig.PRELOAD_DATE)
                _releaseCountdown.value = calculateCountdown(CountdownConfig.RELEASE_DATE)
                delay(1000)
            }
        }
    }

    /**
     * Calcula dias/horas/minutos/segundos restantes até [target].
     * Se a data já passou, retorna tudo zerado com isFinished = true,
     * em vez de mostrar valores negativos (item 8 do planejamento).
     */
    private fun calculateCountdown(target: Instant): CountdownState {
        val now = Instant.now()
        var remaining = Duration.between(now, target)

        if (remaining.isNegative) {
            return CountdownState(days = 0, hours = 0, minutes = 0, seconds = 0, isFinished = true)
        }

        val days = remaining.toDays()
        remaining = remaining.minusDays(days)
        val hours = remaining.toHours()
        remaining = remaining.minusHours(hours)
        val minutes = remaining.toMinutes()
        remaining = remaining.minusMinutes(minutes)
        val seconds = remaining.seconds

        return CountdownState(days, hours, minutes, seconds, isFinished = false)
    }
}
