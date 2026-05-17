package org.example.model

data class Serie(
    val numero: Int,
    val repeticoes: Int,
    val pesoKg: Double,
    val observacao: String = ""
) {
    override fun toString(): String {
        val obs = if (observacao.isNotBlank()) " — $observacao" else ""
        return "  Série $numero: ${repeticoes} reps × ${"%.1f".format(pesoKg)} kg$obs"
    }
}