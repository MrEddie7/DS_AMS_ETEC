package org.example.ui

import com.formdev.flatlaf.FlatDarkLaf
import org.example.service.TreinoService
import java.awt.*
import javax.swing.*

class MainFrame(private val service: TreinoService = TreinoService()) : JFrame() {

    private val painelTreinoAtual = PainelTreinoAtual(service) { atualizarAbas() }
    private val painelHistorico = PainelHistorico(service)
    private val painelEstatisticas = PainelEstatisticas(service)
    private val tabbedPane = JTabbedPane()

    init {
        title = "🏋️ Rastreador de Treinos"
        defaultCloseOperation = EXIT_ON_CLOSE
        size = Dimension(900, 650)
        minimumSize = Dimension(750, 500)
        setLocationRelativeTo(null)

        configurarUI()
    }

    private fun configurarUI() {
        // Header
        val header = JPanel(BorderLayout()).apply {
            border = BorderFactory.createEmptyBorder(12, 16, 8, 16)
            val titulo = JLabel("🏋️ Rastreador de Treinos").apply {
                font = Font("SansSerif", Font.BOLD, 22)
            }
            val subtitulo = JLabel("Controle seus treinos de forma eficiente").apply {
                font = Font("SansSerif", Font.PLAIN, 13)
                foreground = Color.GRAY
            }
            val textos = JPanel().apply {
                layout = BoxLayout(this, BoxLayout.Y_AXIS)
                isOpaque = false
                add(titulo)
                add(Box.createVerticalStrut(2))
                add(subtitulo)
            }
            add(textos, BorderLayout.WEST)
        }

        // Tabs
        tabbedPane.apply {
            font = Font("SansSerif", Font.PLAIN, 14)
            addTab("💪 Treino Atual", painelTreinoAtual)
            addTab("📋 Histórico", painelHistorico)
            addTab("📊 Estatísticas", painelEstatisticas)
            addChangeListener { atualizarAbaAtiva() }
        }

        contentPane.layout = BorderLayout()
        contentPane.add(header, BorderLayout.NORTH)
        contentPane.add(tabbedPane, BorderLayout.CENTER)
    }

    private fun atualizarAbaAtiva() {
        when (tabbedPane.selectedComponent) {
            painelHistorico -> painelHistorico.atualizar()
            painelEstatisticas -> painelEstatisticas.atualizar()
        }
    }

    private fun atualizarAbas() {
        painelHistorico.atualizar()
        painelEstatisticas.atualizar()
    }

    companion object {
        fun iniciar() {
            FlatDarkLaf.setup()
            UIManager.put("TabbedPane.showTabSeparators", true)
            UIManager.put("Button.arc", 8)
            UIManager.put("Component.arc", 8)
            UIManager.put("TextComponent.arc", 8)

            SwingUtilities.invokeLater {
                MainFrame().isVisible = true
            }
        }
    }
}
