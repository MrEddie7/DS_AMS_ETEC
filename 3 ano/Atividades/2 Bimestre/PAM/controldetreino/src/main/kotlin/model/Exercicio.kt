package org.example.model

data class Exercicio(
    val nome: String,
    val tipo: TipoExercicio,
    val series: MutableList<Serie> = mutableListOf()
) {
    val volumeTotal: Double
        get() = series.sumOf { it.repeticoes * it.pesoKg }

    fun adicionarSerie(repeticoes: Int, pesoKg: Double, observacao: String = "") {
        val numero = series.size + 1
        series.add(Serie(numero, repeticoes, pesoKg, observacao))
    }

    override fun toString(): String {
        val header = "▸ $nome [${tipo.descricao}] — Volume total: ${"%.1f".format(volumeTotal)} kg"
        val seriesStr = series.joinToString("\n")
        return "$header\n$seriesStr"
    }
}