package org.example.project.domain

import kotlin.jvm.JvmInline

@JvmInline
value class TreinoId(val value: Long)

data class Exercicio(
    val id: Long,
    val nome: String,
    val series: Int,
    val repeticoes: Int,
    val cargaKg: Double,
    val concluido: Boolean = false
) {
    fun volume(): Double = series * repeticoes * cargaKg
}

data class Treino(
    val id: TreinoId,
    val nome: String,
    val descricao: String = "",
    val exercicios: List<Exercicio> = emptyList()
) {
    val totalExercicios: Int get() = exercicios.size
    val concluidos: Int get() = exercicios.count { it.concluido }
    val progresso: Float
        get() = if (totalExercicios == 0) 0f else concluidos.toFloat() / totalExercicios
}
