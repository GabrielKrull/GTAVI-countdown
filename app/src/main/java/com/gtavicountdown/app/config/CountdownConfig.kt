package com.gtavicountdown.app.config

import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

/**
 * Fonte única de verdade para todas as datas relacionadas ao countdown.
 *
 * Por que centralizar aqui?
 * A Rockstar já mudou a data de GTA VI duas vezes desde o anúncio original.
 * Se isso acontecer de novo, só precisamos editar ESTE arquivo — nenhuma
 * outra parte do app deve conter uma data "espalhada" pelo código.
 *
 * Por que Instant e não LocalDateTime?
 * Instant representa um ponto exato e único no tempo, em UTC, independente
 * de onde o usuário está. Isso evita o bug mais comum em countdowns
 * internacionais: dois usuários em fusos diferentes verem contagens
 * inconsistentes. A conversão para o horário local acontece só na hora
 * de EXIBIR — nunca no armazenamento. Vamos usar exatamente esse padrão
 * no HomeViewModel.
 */
object CountdownConfig {

    /**
     * Fuso oficial do projeto para as duas datas principais (v0.1).
     * Centralizado aqui para facilitar suporte a outros fusos no futuro.
     */
    private val ZONE_SAO_PAULO: ZoneId = ZoneId.of("America/Sao_Paulo")

    /**
     * Lançamento oficial: 19 de novembro de 2026, meia-noite em Brasília.
     * Ou seja: 19/11/2026 00:00 America/Sao_Paulo (= 19/11/2026 03:00 UTC).
     *
     * A Rockstar confirmou que o lançamento acontece à meia-noite local
     * em cada fuso horário. Para a v0.1, fixamos Brasília como referência.
     */
    val RELEASE_DATE: Instant = LocalDate.of(2026, 11, 19)
        .atTime(LocalTime.MIDNIGHT)
        .atZone(ZONE_SAO_PAULO)
        .toInstant()

    /**
     * Liberação do pré-carregamento / download: 12 de novembro de 2026,
     * meia-noite em Brasília. Tratada como configuração do projeto
     * (data não oficialmente confirmada pela Rockstar).
     */
    val PRELOAD_DATE: Instant = LocalDate.of(2026, 11, 12)
        .atTime(LocalTime.MIDNIGHT)
        .atZone(ZONE_SAO_PAULO)
        .toInstant()
}
