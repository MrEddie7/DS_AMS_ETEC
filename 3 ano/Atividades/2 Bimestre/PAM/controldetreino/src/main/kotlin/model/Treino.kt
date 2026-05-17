package org.example.model

import java.time.LocalDate
import java.time.LocalTime
import java.time.Duration
import java.time.format.DateTimeFormatter

data class Treino(
    val id: Int,
    val nome: String,
    val data: LocalDate = LocalDate.now(),
    val horaInicio: LocalTime = LocalTime.now(),
    var horaFim: LocalTime? = null,
    val exercicios: MutableList<Exercicio> = mutableListOf(),
    var nota: String = ""
) {
    val duracaoMinutos: Long?
        get() = horaFim?.let { Duration.between(horaInicio, it).toMinutes() }

    val volumeTotal: Double
        get() = exercicios.sumOf { it.volumeTotal }

    val totalSeries: Int
        get() = exercicios.sumOf { it.series.size }

    fun finalizar() {
        horaFim = LocalTime.now()
    }

    fun resumo(): String {
        val dtFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val hrFmt = DateTimeFormatter.ofPattern("HH:mm")
        val duracao = duracaoMinutos?.let { "${it} min" } ?: "em andamento"
        return buildString {
            appendLine("═══════════════════════════════════════════")
            appendLine("🏋️ Treino #$id: $nome")
            appendLine("📅 ${data.format(dtFmt)}  ⏰ ${horaInicio.format(hrFmt)} ($duracao)")
            appendLine("📊 ${exercicios.size} exercícios | $totalSeries séries | Volume: ${"%.1f".format(volumeTotal)} kg")
            if (nota.isNotBlank()) appendLine("📝 $nota")
            appendLine("───────────────────────────────────────────")
            exercicios.forEach { appendLine(it) }
            appendLine("═══════════════════════════════════════════")
        }
    }
}