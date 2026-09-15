package com.gtavicountdown.app.config

import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

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
     * Lançamento oficial: 19 de novembro de 2026.
     * A Rockstar confirmou que o lançamento acontece à meia-noite local
     * em cada fuso horário. Para a v0.1, simplificamos usando meia-noite
     * UTC como referência; isso será refinado quando tratarmos fuso
     * horário por região (ver item 17 do planejamento).
     */
    val RELEASE_DATE: Instant = ZonedDateTime.of(
        2026, 11, 19, 0, 0, 0, 0, ZoneOffset.UTC
    ).toInstant()

    /** Liberação do pré-carregamento / download: 12 de novembro de 2026. */
    val PRELOAD_DATE: Instant = ZonedDateTime.of(
        2026, 11, 12, 0, 0, 0, 0, ZoneOffset.UTC
    ).toInstant()
}
