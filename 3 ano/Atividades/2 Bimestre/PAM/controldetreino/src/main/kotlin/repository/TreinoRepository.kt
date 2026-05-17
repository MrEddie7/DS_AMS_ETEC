package org.example.repository

import org.example.model.Treino
import org.example.model.TipoExercicio
import java.time.LocalDate

class TreinoRepository {
    private val treinos = mutableListOf<Treino>()
    private var proximoId = 1

    fun gerarId(): Int = proximoId++

    fun salvar(treino: Treino) {
        treinos.add(treino)
    }

    fun listarTodos(): List<Treino> = treinos.toList()

    fun buscarPorId(id: Int): Treino? = treinos.find { it.id == id }

    fun buscarPorData(data: LocalDate): List<Treino> =
        treinos.filter { it.data == data }

    fun buscarPorGrupoMuscular(tipo: TipoExercicio): List<Treino> =
        treinos.filter { treino -> treino.exercicios.any { it.tipo == tipo } }

    fun remover(id: Int): Boolean =
        treinos.removeIf { it.id == id }

    fun totalTreinos(): Int = treinos.size

    fun volumeTotalHistorico(): Double =
        treinos.sumOf { it.volumeTotal }

    fun recordePesoPorExercicio(): Map<String, Double> {
        return treinos.flatMap { it.exercicios }
            .flatMap { ex -> ex.series.map { ex.nome to it.pesoKg } }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.max() }
    }
}