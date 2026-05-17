package org.example.ui

import org.example.model.TipoExercicio
import org.example.service.TreinoService
import java.awt.*
import java.time.format.DateTimeFormatter
import javax.swing.*
import javax.swing.border.TitledBorder
import javax.swing.table.DefaultTableModel

class PainelHistorico(private val service: TreinoService) : JPanel(BorderLayout(0, 10)) {

    private val tabelaModel = DefaultTableModel(
        arrayOf("ID", "Nome", "Data", "Início", "Duração", "Exercícios", "Séries", "Volume (kg)", "Nota"), 0
    ).apply {  fun isCellEditable(row: Int, col: Int) = false }
    private val tabela = JTable(tabelaModel)

    private val detalhesModel = DefaultTableModel(
        arrayOf("Exercício", "Grupo", "Série #", "Reps", "Peso (kg)", "Obs"), 0
    ).apply { fun isCellEditable(row: Int, col: Int) = false }
    private val tabelaDetalhes = JTable(detalhesModel)

    private val cmbFiltroGrupo = JComboBox<String>()
    private val btnRemover = JButton("🗑️ Remover Treino")

    init {
        border = BorderFactory.createEmptyBorder(10, 16, 10, 16)
        configurarUI()
        configurarEventos()
    }

    private fun configurarUI() {
        // Barra de ferramentas
        val toolbar = JPanel(FlowLayout(FlowLayout.LEFT, 8, 4)).apply {
            add(JLabel("Filtrar por grupo:"))
            cmbFiltroGrupo.addItem("Todos")
            TipoExercicio.entries.forEach { cmbFiltroGrupo.addItem(it.descricao) }
            add(cmbFiltroGrupo)
            add(Box.createHorizontalStrut(20))
            btnRemover.apply { background = Color(198, 40, 40); foreground = Color.WHITE }
            add(btnRemover)
        }

        tabela.selectionModel.selectionMode = ListSelectionModel.SINGLE_SELECTION

        val splitPane = JSplitPane(JSplitPane.VERTICAL_SPLIT).apply {
            topComponent = JScrollPane(tabela).apply {
                border = titledBorder("Treinos")
                preferredSize = Dimension(0, 200)
            }
            bottomComponent = JScrollPane(tabelaDetalhes).apply {
                border = titledBorder("Detalhes do Treino Selecionado")
                preferredSize = Dimension(0, 200)
            }
            resizeWeight = 0.5
        }

        add(toolbar, BorderLayout.NORTH)
        add(splitPane, BorderLayout.CENTER)
    }

    private fun configurarEventos() {
        tabela.selectionModel.addListSelectionListener {
            if (!it.valueIsAdjusting) exibirDetalhes()
        }

        cmbFiltroGrupo.addActionListener { atualizar() }

        btnRemover.addActionListener {
            val row = tabela.selectedRow
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um treino para remover.", "Aviso", JOptionPane.WARNING_MESSAGE)
                return@addActionListener
            }
            val id = tabelaModel.getValueAt(row, 0) as Int
            val confirma = JOptionPane.showConfirmDialog(
                this, "Remover treino #$id?", "Confirmar", JOptionPane.YES_NO_OPTION
            )
            if (confirma == JOptionPane.YES_OPTION) {
                service.removerTreino(id)
                atualizar()
            }
        }
    }

    fun atualizar() {
        tabelaModel.rowCount = 0
        detalhesModel.rowCount = 0
        val dtFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val hrFmt = DateTimeFormatter.ofPattern("HH:mm")

        val filtroIdx = cmbFiltroGrupo.selectedIndex
        val treinos = if (filtroIdx <= 0) {
            service.historico()
        } else {
            val tipo = TipoExercicio.entries[filtroIdx - 1]
            service.buscarPorGrupo(tipo)
        }

        treinos.forEach { t ->
            val duracao = t.duracaoMinutos?.let { "${it} min" } ?: "—"
            tabelaModel.addRow(arrayOf(
                t.id, t.nome, t.data.format(dtFmt), t.horaInicio.format(hrFmt),
                duracao, t.exercicios.size, t.totalSeries,
                "%.1f".format(t.volumeTotal), t.nota
            ))
        }
    }

    private fun exibirDetalhes() {
        detalhesModel.rowCount = 0
        val row = tabela.selectedRow
        if (row < 0) return
        val id = tabelaModel.getValueAt(row, 0) as Int
        val treino = service.buscarTreinoPorId(id) ?: return
        treino.exercicios.forEach { ex ->
            ex.series.forEach { s ->
                detalhesModel.addRow(arrayOf(
                    ex.nome, ex.tipo.descricao, s.numero, s.repeticoes,
                    "%.1f".format(s.pesoKg), s.observacao
                ))
            }
        }
    }

    private fun titledBorder(titulo: String) =
        BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color(80, 80, 80)),
            titulo, TitledBorder.LEFT, TitledBorder.TOP,
            Font("SansSerif", Font.BOLD, 12)
        )
}