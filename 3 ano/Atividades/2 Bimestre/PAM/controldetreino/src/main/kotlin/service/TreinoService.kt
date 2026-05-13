package org.example.service

import org.example.model.*
import org.example.repository.TreinoRepository
import java.time.LocalDate

class TreinoService(private val repository: TreinoRepository = TreinoRepository()) {

    private var treinoAtual: Treino? = null

    fun iniciarTreino(nome: String): Treino {
        if (treinoAtual != null) {
            throw IllegalStateException("Já existe um treino em andamento! Finalize-o antes de iniciar outro.")
        }
        val treino = Treino(id = repository.gerarId(), nome = nome)
        treinoAtual = treino
        return treino
    }

    fun obterTreinoAtual(): Treino? = treinoAtual

    fun adicionarExercicio(nome: String, tipo: TipoExercicio): Exercicio {
        val treino = treinoAtual
            ?: throw IllegalStateException("Nenhum treino em andamento. Inicie um treino primeiro.")
        val exercicio = Exercicio(nome = nome, tipo = tipo)
        treino.exercicios.add(exercicio)
        return exercicio
    }

    fun adicionarSerie(indiceExercicio: Int, repeticoes: Int, pesoKg: Double, observacao: String = "") {
        val treino = treinoAtual
            ?: throw IllegalStateException("Nenhum treino em andamento.")
        val exercicio = treino.exercicios.getOrNull(indiceExercicio)
            ?: throw IndexOutOfBoundsException("Exercício não encontrado no índice $indiceExercicio.")
        exercicio.adicionarSerie(repeticoes, pesoKg, observacao)
    }

    fun finalizarTreino(nota: String = ""): Treino {
        val treino = treinoAtual
            ?: throw IllegalStateException("Nenhum treino em andamento.")
        treino.finalizar()
        treino.nota = nota
        repository.salvar(treino)
        treinoAtual = null
        return treino
    }

    fun cancelarTreino() {
        treinoAtual = null
    }

    fun historico(): List<Treino> = repository.listarTodos()

    fun buscarTreinoPorId(id: Int): Treino? = repository.buscarPorId(id)

    fun buscarPorData(data: LocalDate): List<Treino> = repository.buscarPorData(data)

    fun buscarPorGrupo(tipo: TipoExercicio): List<Treino> = repository.buscarPorGrupoMuscular(tipo)

    fun removerTreino(id: Int): Boolean = repository.remover(id)

    fun estatisticas(): String {
        val total = repository.totalTreinos()
        val volumeTotal = repository.volumeTotalHistorico()
        val recordes = repository.recordePesoPorExercicio()
        return buildString {
            appendLine("╔═══════════════════════════════════════════╗")
            appendLine("║        📊 ESTATÍSTICAS GERAIS             ║")
            appendLine("╠═══════════════════════════════════════════╣")
            appendLine("║ Total de treinos: $total")
            appendLine("║ Volume total acumulado: ${"%.1f".format(volumeTotal)} kg")
            if (recordes.isNotEmpty()) {
                appendLine("║───────────────────────────────────────────")
                appendLine("║ 🏆 Recordes de peso por exercício:")
                recordes.forEach { (nome, peso) ->
                    appendLine("║   $nome: ${"%.1f".format(peso)} kg")
                }
            }
            appendLine("╚═══════════════════════════════════════════╝")
        }
    }
}