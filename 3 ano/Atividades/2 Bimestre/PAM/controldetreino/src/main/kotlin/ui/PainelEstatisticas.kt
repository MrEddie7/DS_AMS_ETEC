package org.example.ui

import org.example.service.TreinoService
import java.awt.*
import javax.swing.*

class PainelEstatisticas(private val service: TreinoService) : JPanel(BorderLayout(0, 16)) {

    private val painelCards = JPanel(GridLayout(1, 4, 12, 0))
    private val lblTotalTreinos = criarLabelValor("0")
    private val lblTotalExercicios = criarLabelValor("0")
    private val lblTotalSeries = criarLabelValor("0")
    private val lblVolumeTotal = criarLabelValor("0.0 kg")

    private val painelRecordes = JPanel()

    init {
        border = BorderFactory.createEmptyBorder(16, 16, 16, 16)
        configurarUI()
    }

    private fun configurarUI() {
        // Cards de resumo
        painelCards.apply {
            isOpaque = false
            add(criarCard("🏋️ Treinos", lblTotalTreinos))
            add(criarCard("💪 Exercícios", lblTotalExercicios))
            add(criarCard("🔁 Séries", lblTotalSeries))
            add(criarCard("📦 Volume Total", lblVolumeTotal))
        }

        // Painel de recordes
        painelRecordes.apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color(80, 80, 80)),
                "🏆 Recordes de Peso por Exercício"
            )
        }

        add(painelCards, BorderLayout.NORTH)
        add(JScrollPane(painelRecordes).apply {
            border = BorderFactory.createEmptyBorder()
        }, BorderLayout.CENTER)
    }

    fun atualizar() {
        val historico = service.historico()
        val totalTreinos = historico.size
        val totalExercicios = historico.sumOf { it.exercicios.size }
        val totalSeries = historico.sumOf { it.totalSeries }
        val volumeTotal = historico.sumOf { it.volumeTotal }

        lblTotalTreinos.text = "$totalTreinos"
        lblTotalExercicios.text = "$totalExercicios"
        lblTotalSeries.text = "$totalSeries"
        lblVolumeTotal.text = "%.1f kg".format(volumeTotal)

        // Recordes
        painelRecordes.removeAll()
        val recordes = historico
            .flatMap { it.exercicios }
            .flatMap { ex -> ex.series.map { ex.nome to it.pesoKg } }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.max() }
            .toList()
            .sortedByDescending { it.second }

        if (recordes.isEmpty()) {
            painelRecordes.add(JLabel("   Nenhum recorde registrado ainda.").apply {
                foreground = Color.GRAY
                font = Font("SansSerif", Font.ITALIC, 13)
                border = BorderFactory.createEmptyBorder(12, 0, 0, 0)
            })
        } else {
            recordes.forEachIndexed { index, (nome, peso) ->
                val medalha = when (index) {
                    0 -> "🥇"
                    1 -> "🥈"
                    2 -> "🥉"
                    else -> "   "
                }
                val lbl = JLabel("  $medalha  $nome — ${"%.1f".format(peso)} kg").apply {
                    font = Font("SansSerif", Font.PLAIN, 14)
                    border = BorderFactory.createEmptyBorder(4, 8, 4, 8)
                    maximumSize = Dimension(Int.MAX_VALUE, 30)
                }
                painelRecordes.add(lbl)
            }
        }

        painelRecordes.revalidate()
        painelRecordes.repaint()
    }

    private fun criarCard(titulo: String, valorLabel: JLabel): JPanel {
        return JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            border = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color(70, 70, 70), 1, true),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
            )
            val tituloLbl = JLabel(titulo).apply {
                font = Font("SansSerif", Font.PLAIN, 12)
                foreground = Color(180, 180, 180)
                alignmentX = CENTER_ALIGNMENT
            }
            valorLabel.alignmentX = CENTER_ALIGNMENT
            add(tituloLbl)
            add(Box.createVerticalStrut(6))
            add(valorLabel)
        }
    }

    private fun criarLabelValor(texto: String) = JLabel(texto).apply {
        font = Font("SansSerif", Font.BOLD, 28)
        foreground = Color(100, 181, 246)
    }
}