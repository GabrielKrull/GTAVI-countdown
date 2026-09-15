package com.gtavicountdown.app.ui.home

/**
 * Representa o estado JÁ CALCULADO de uma contagem regressiva, pronto
 * para a UI exibir sem precisar saber nada sobre datas ou fusos horários.
 *
 * Separar esse modelo do restante da lógica é o que permite ao Compose
 * apenas "desenhar" o que recebe, sem se preocupar em como foi calculado.
 */
data class CountdownState(
    val days: Long,
    val hours: Long,
    val minutes: Long,
    val seconds: Long,
    val isFinished: Boolean
)
