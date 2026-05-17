package org.example.ui

import org.example.model.TipoExercicio
import org.example.service.TreinoService

class MenuPrincipal(private val service: TreinoService = TreinoService()) {

    fun executar() {
        println("""
            
            ╔═══════════════════════════════════════════╗
            ║     🏋️  RASTREADOR DE TREINOS  🏋️        ║
            ╚═══════════════════════════════════════════╝
        """.trimIndent())

        var rodando = true
        while (rodando) {
            exibirMenu()
            when (lerOpcao()) {
                1 -> iniciarTreino()
                2 -> adicionarExercicio()
                3 -> adicionarSerie()
                4 -> verTreinoAtual()
                5 -> finalizarTreino()
                6 -> verHistorico()
                7 -> verEstatisticas()
                8 -> buscarPorGrupo()
                9 -> removerTreino()
                0 -> {
                    println("\n👋 Até o próximo treino! Bons ganhos! 💪")
                    rodando = false
                }
                else -> println("⚠️ Opção inválida. Tente novamente.")
            }
        }
    }

    private fun exibirMenu() {
        println("""
            
            ┌───────────────────────────────────────────┐
            │              MENU PRINCIPAL               │
            ├───────────────────────────────────────────┤
            │  1. Iniciar novo treino                   │
            │  2. Adicionar exercício ao treino atual    │
            │  3. Registrar série em um exercício        │
            │  4. Ver treino atual                       │
            │  5. Finalizar treino                       │
            │  6. Ver histórico de treinos               │
            │  7. Ver estatísticas                       │
            │  8. Buscar por grupo muscular              │
            │  9. Remover treino do histórico            │
            │  0. Sair                                   │
            └───────────────────────────────────────────┘
        """.trimIndent())
        print("👉 Escolha uma opção: ")
    }

    private fun lerOpcao(): Int = readlnOrNull()?.trim()?.toIntOrNull() ?: -1

    private fun lerTexto(prompt: String): String {
        print(prompt)
        return readlnOrNull()?.trim().orEmpty()
    }

    private fun lerDouble(prompt: String): Double {
        print(prompt)
        return readlnOrNull()?.trim()?.replace(",", ".")?.toDoubleOrNull() ?: 0.0
    }

    private fun lerInt(prompt: String): Int {
        print(prompt)
        return readlnOrNull()?.trim()?.toIntOrNull() ?: 0
    }

    // ── Ações do menu ───────────────────────────────────

    private fun iniciarTreino() {
        try {
            val nome = lerTexto("📝 Nome do treino (ex: Treino A - Peito e Tríceps): ")
            if (nome.isBlank()) {
                println("⚠️ O nome do treino não pode estar vazio.")
                return
            }
            val treino = service.iniciarTreino(nome)
            println("✅ Treino #${treino.id} '${treino.nome}' iniciado! Adicione exercícios.")
        } catch (e: IllegalStateException) {
            println("⚠️ ${e.message}")
        }
    }

    private fun adicionarExercicio() {
        try {
            val nome = lerTexto("💪 Nome do exercício: ")
            if (nome.isBlank()) {
                println("⚠️ O nome do exercício não pode estar vazio.")
                return
            }

            println("\nGrupos musculares:")
            TipoExercicio.entries.forEachIndexed { i, tipo ->
                println("  $i. ${tipo.descricao}")
            }
            val tipoIdx = lerInt("Escolha o grupo muscular: ")
            val tipo = TipoExercicio.fromOrdinal(tipoIdx)
            if (tipo == null) {
                println("⚠️ Grupo muscular inválido.")
                return
            }

            val exercicio = service.adicionarExercicio(nome, tipo)
            println("✅ Exercício '${exercicio.nome}' [${exercicio.tipo.descricao}] adicionado!")
        } catch (e: IllegalStateException) {
            println("⚠️ ${e.message}")
        }
    }

    private fun adicionarSerie() {
        try {
            val treino = service.obterTreinoAtual()
            if (treino == null) {
                println("⚠️ Nenhum treino em andamento.")
                return
            }
            if (treino.exercicios.isEmpty()) {
                println("⚠️ Adicione um exercício antes de registrar séries.")
                return
            }

            println("\nExercícios no treino atual:")
            treino.exercicios.forEachIndexed { i, ex ->
                println("  $i. ${ex.nome} [${ex.tipo.descricao}] — ${ex.series.size} séries")
            }

            val idx = lerInt("Escolha o exercício (número): ")
            val reps = lerInt("Repetições: ")
            val peso = lerDouble("Peso (kg): ")
            val obs = lerTexto("Observação (Enter para pular): ")

            service.adicionarSerie(idx, reps, peso, obs)
            println("✅ Série registrada com sucesso!")
        } catch (e: Exception) {
            println("⚠️ ${e.message}")
        }
    }

    private fun verTreinoAtual() {
        val treino = service.obterTreinoAtual()
        if (treino == null) {
            println("ℹ️ Nenhum treino em andamento no momento.")
            return
        }
        println(treino.resumo())
    }

    private fun finalizarTreino() {
        try {
            val nota = lerTexto("📝 Alguma nota sobre o treino? (Enter para pular): ")
            val treino = service.finalizarTreino(nota)
            println("\n🎉 Treino finalizado com sucesso!")
            println(treino.resumo())
        } catch (e: IllegalStateException) {
            println("⚠️ ${e.message}")
        }
    }

    private fun verHistorico() {
        val historico = service.historico()
        if (historico.isEmpty()) {
            println("ℹ️ Nenhum treino registrado ainda.")
            return
        }
        println("\n📋 HISTÓRICO DE TREINOS (${historico.size} treinos):")
        historico.forEach { println(it.resumo()) }
    }

    private fun verEstatisticas() {
        println(service.estatisticas())
    }

    private fun buscarPorGrupo() {
        println("\nGrupos musculares:")
        TipoExercicio.entries.forEachIndexed { i, tipo ->
            println("  $i. ${tipo.descricao}")
        }
        val idx = lerInt("Escolha o grupo: ")
        val tipo = TipoExercicio.fromOrdinal(idx)
        if (tipo == null) {
            println("⚠️ Grupo inválido.")
            return
        }
        val treinos = service.buscarPorGrupo(tipo)
        if (treinos.isEmpty()) {
            println("ℹ️ Nenhum treino encontrado para '${tipo.descricao}'.")
            return
        }
        println("\n🔍 Treinos com exercícios de ${tipo.descricao}:")
        treinos.forEach { println(it.resumo()) }
    }

    private fun removerTreino() {
        val id = lerInt("🗑️ ID do treino a remover: ")
        if (service.removerTreino(id)) {
            println("✅ Treino #$id removido.")
        } else {
            println("⚠️ Treino #$id não encontrado.")
        }
    }
}