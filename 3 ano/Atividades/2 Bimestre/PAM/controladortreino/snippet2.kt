package org.example.project.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.example.project.domain.Exercicio
import org.example.project.domain.Treino
import org.example.project.domain.TreinoId

class TreinoRepository {

    private var nextTreinoId: Long = 1
    private var nextExercicioId: Long = 1

    private val _treinos = MutableStateFlow<List<Treino>>(seed())
    val treinos: StateFlow<List<Treino>> = _treinos.asStateFlow()

    fun adicionarTreino(nome: String, descricao: String): TreinoId {
        val id = TreinoId(nextTreinoId++)
        _treinos.value = _treinos.value + Treino(id, nome, descricao)
        return id
    }

    fun removerTreino(id: TreinoId) {
        _treinos.value = _treinos.value.filterNot { it.id == id }
    }

    fun adicionarExercicio(
        treinoId: TreinoId, nome: String, series: Int, repeticoes: Int, cargaKg: Double
    ) = update(treinoId) { t ->
        t.copy(exercicios = t.exercicios + Exercicio(nextExercicioId++, nome, series, repeticoes, cargaKg))
    }

    fun alternarConclusao(treinoId: TreinoId, exercicioId: Long) = update(treinoId) { t ->
        t.copy(exercicios = t.exercicios.map {
            if (it.id == exercicioId) it.copy(concluido = !it.concluido) else it
        })
    }

    fun removerExercicio(treinoId: TreinoId, exercicioId: Long) = update(treinoId) { t ->
        t.copy(exercicios = t.exercicios.filterNot { it.id == exercicioId })
    }

    private inline fun update(id: TreinoId, transform: (Treino) -> Treino) {
        _treinos.value = _treinos.value.map { if (it.id == id) transform(it) else it }
    }

    private fun seed(): List<Treino> = listOf(
        Treino(
            id = TreinoId(nextTreinoId++),
            nome = "Treino A - Peito e Tríceps",
            descricao = "Foco em hipertrofia",
            exercicios = listOf(
                Exercicio(nextExercicioId++, "Supino reto", 4, 10, 40.0),
                Exercicio(nextExercicioId++, "Crucifixo", 3, 12, 14.0),
                Exercicio(nextExercicioId++, "Tríceps corda", 4, 12, 25.0),
            )
        )
    )
}
