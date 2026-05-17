package org.example.ui

import org.example.model.TipoExercicio
import org.example.service.TreinoService
import java.awt.*
import javax.swing.*
import javax.swing.border.TitledBorder
import javax.swing.table.DefaultTableModel

class PainelTreinoAtual(
    private val service: TreinoService,
    private val onTreinoFinalizado: () -> Unit
) : JPanel(BorderLayout(0, 10)) {

    // Componentes do painel de iniciar treino
    private val txtNomeTreino = JTextField(20)
    private val btnIniciar = JButton("▶ Iniciar Treino")
    private val btnFinalizar = JButton("✅ Finalizar Treino")
    private val btnCancelar = JButton("❌ Cancelar")
    private val lblStatus = JLabel("Nenhum treino em andamento")

    // Componentes de exercícios
    private val txtNomeExercicio = JTextField(15)
    private val cmbTipoExercicio = JComboBox(TipoExercicio.entries.toTypedArray())
    private val btnAddExercicio = JButton("+ Exercício")

    // Componentes de séries
    private val cmbExercicio = JComboBox<String>()
    private val spnRepeticoes = JSpinner(SpinnerNumberModel(12, 1, 999, 1))
    private val spnPeso = JSpinner(SpinnerNumberModel(0.0, 0.0, 9999.0, 2.5))
    private val txtObservacao = JTextField(10)
    private val btnAddSerie = JButton("+ Série")

    // Tabela de resumo
    private val tabelaModel = DefaultTableModel(
        arrayOf("Exercício", "Grupo", "Séries", "Volume (kg)"), 0
    ).apply { fun isCellEditable(row: Int, col: Int) = false }
    private val tabela = JTable(tabelaModel)

    // Tabela de séries detalhadas
    private val seriesModel = DefaultTableModel(
        arrayOf("Exercício", "Série #", "Reps", "Peso (kg)", "Obs"), 0
    ).apply { fun isCellEditable(row: Int, col: Int) = false }
    private val tabelaSeries = JTable(seriesModel)

    init {
        border = BorderFactory.createEmptyBorder(10, 16, 10, 16)
        configurarUI()
        configurarEventos()
        atualizarEstado()
    }

    private fun configurarUI() {
        // ── Topo: Iniciar/Finalizar Treino ──
        val painelTopo = JPanel(FlowLayout(FlowLayout.LEFT, 8, 4)).apply {
            border = titledBorder("Treino")
            add(JLabel("Nome:"))
            add(txtNomeTreino)
            add(btnIniciar)
            add(btnFinalizar)
            add(btnCancelar)
            add(Box.createHorizontalStrut(12))
            add(lblStatus)
        }

        // ── Meio: Adicionar exercício + série ──
        val painelExercicio = JPanel(FlowLayout(FlowLayout.LEFT, 8, 4)).apply {
            border = titledBorder("Adicionar Exercício")
            add(JLabel("Nome:"))
            add(txtNomeExercicio)
            add(JLabel("Grupo:"))
            cmbTipoExercicio.renderer = object : DefaultListCellRenderer() {
                override fun getListCellRendererComponent(
                    list: JList<*>?, value: Any?, index: Int,
                    isSelected: Boolean, cellHasFocus: Boolean
                ): Component {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)
                    text = (value as? TipoExercicio)?.descricao ?: ""
                    return this
                }
            }
            add(cmbTipoExercicio)
            add(btnAddExercicio)
        }

        val painelSerie = JPanel(FlowLayout(FlowLayout.LEFT, 8, 4)).apply {
            border = titledBorder("Registrar Série")
            add(JLabel("Exercício:"))
            cmbExercicio.preferredSize = Dimension(180, 28)
            add(cmbExercicio)
            add(JLabel("Reps:"))
            spnRepeticoes.preferredSize = Dimension(60, 28)
            add(spnRepeticoes)
            add(JLabel("Peso (kg):"))
            spnPeso.preferredSize = Dimension(80, 28)
            add(spnPeso)
            add(JLabel("Obs:"))
            add(txtObservacao)
            add(btnAddSerie)
        }

        val painelControles = JPanel().apply {
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            add(painelTopo)
            add(painelExercicio)
            add(painelSerie)
        }

        // ── Tabelas ──
        val splitTabelas = JSplitPane(JSplitPane.VERTICAL_SPLIT).apply {
            topComponent = JScrollPane(tabela).apply {
                border = titledBorder("Exercícios do Treino")
                preferredSize = Dimension(0, 160)
            }
            bottomComponent = JScrollPane(tabelaSeries).apply {
                border = titledBorder("Séries Detalhadas")
                preferredSize = Dimension(0, 180)
            }
            resizeWeight = 0.45
        }

        add(painelControles, BorderLayout.NORTH)
        add(splitTabelas, BorderLayout.CENTER)

        // Estilos dos botões
        btnIniciar.apply { background = Color(46, 125, 50); foreground = Color.WHITE }
        btnFinalizar.apply { background = Color(21, 101, 192); foreground = Color.WHITE }
        btnCancelar.apply { background = Color(198, 40, 40); foreground = Color.WHITE }
        btnAddExercicio.apply { background = Color(56, 142, 60); foreground = Color.WHITE }
        btnAddSerie.apply { background = Color(56, 142, 60); foreground = Color.WHITE }
    }

    private fun configurarEventos() {
        btnIniciar.addActionListener { iniciarTreino() }
        btnFinalizar.addActionListener { finalizarTreino() }
        btnCancelar.addActionListener { cancelarTreino() }
        btnAddExercicio.addActionListener { adicionarExercicio() }
        btnAddSerie.addActionListener { adicionarSerie() }
    }

    private fun iniciarTreino() {
        val nome = txtNomeTreino.text.trim()
        if (nome.isBlank()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do treino.", "Aviso", JOptionPane.WARNING_MESSAGE)
            return
        }
        try {
            val treino = service.iniciarTreino(nome)
            lblStatus.text = "🟢 Treino #${treino.id}: ${treino.nome} — em andamento"
            lblStatus.foreground = Color(76, 175, 80)
            txtNomeTreino.text = ""
            atualizarEstado()
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(this, e.message, "Erro", JOptionPane.ERROR_MESSAGE)
        }
    }

    private fun adicionarExercicio() {
        val nome = txtNomeExercicio.text.trim()
        if (nome.isBlank()) {
            JOptionPane.showMessageDialog(this, "Informe o nome do exercício.", "Aviso", JOptionPane.WARNING_MESSAGE)
            return
        }
        val tipo = cmbTipoExercicio.selectedItem as TipoExercicio
        try {
            service.adicionarExercicio(nome, tipo)
            txtNomeExercicio.text = ""
            atualizarTabelas()
            atualizarComboExercicios()
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(this, e.message, "Erro", JOptionPane.ERROR_MESSAGE)
        }
    }

    private fun adicionarSerie() {
        val idx = cmbExercicio.selectedIndex
        if (idx < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um exercício.", "Aviso", JOptionPane.WARNING_MESSAGE)
            return
        }
        val reps = spnRepeticoes.value as Int
        val peso = spnPeso.value as Double
        val obs = txtObservacao.text.trim()
        try {
            service.adicionarSerie(idx, reps, peso, obs)
            txtObservacao.text = ""
            atualizarTabelas()
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(this, e.message, "Erro", JOptionPane.ERROR_MESSAGE)
        }
    }

    private fun finalizarTreino() {
        val nota = JOptionPane.showInputDialog(
            this, "Alguma nota sobre o treino?", "Finalizar Treino", JOptionPane.QUESTION_MESSAGE
        ) ?: return
        try {
            service.finalizarTreino(nota)
            lblStatus.text = "✅ Treino finalizado com sucesso!"
            lblStatus.foreground = Color(33, 150, 243)
            atualizarEstado()
            atualizarTabelas()
            atualizarComboExercicios()
            onTreinoFinalizado()
        } catch (e: Exception) {
            JOptionPane.showMessageDialog(this, e.message, "Erro", JOptionPane.ERROR_MESSAGE)
        }
    }

    private fun cancelarTreino() {
        val confirma = JOptionPane.showConfirmDialog(
            this, "Tem certeza que deseja cancelar o treino?", "Cancelar Treino", JOptionPane.YES_NO_OPTION
        )
        if (confirma == JOptionPane.YES_OPTION) {
            service.cancelarTreino()
            lblStatus.text = "Nenhum treino em andamento"
            lblStatus.foreground = Color.GRAY
            atualizarEstado()
            atualizarTabelas()
            atualizarComboExercicios()
        }
    }

    private fun atualizarEstado() {
        val emAndamento = service.obterTreinoAtual() != null
        btnIniciar.isEnabled = !emAndamento
        txtNomeTreino.isEnabled = !emAndamento
        btnFinalizar.isEnabled = emAndamento
        btnCancelar.isEnabled = emAndamento
        btnAddExercicio.isEnabled = emAndamento
        btnAddSerie.isEnabled = emAndamento
        cmbExercicio.isEnabled = emAndamento
        txtNomeExercicio.isEnabled = emAndamento
        cmbTipoExercicio.isEnabled = emAndamento
        spnRepeticoes.isEnabled = emAndamento
        spnPeso.isEnabled = emAndamento
        txtObservacao.isEnabled = emAndamento
    }

    private fun atualizarTabelas() {
        tabelaModel.rowCount = 0
        seriesModel.rowCount = 0
        val treino = service.obterTreinoAtual() ?: return
        treino.exercicios.forEach { ex ->
            tabelaModel.addRow(arrayOf(
                ex.nome, ex.tipo.descricao, ex.series.size, "%.1f".format(ex.volumeTotal)
            ))
            ex.series.forEach { s ->
                seriesModel.addRow(arrayOf(
                    ex.nome, s.numero, s.repeticoes, "%.1f".format(s.pesoKg), s.observacao
                ))
            }
        }
    }

    private fun atualizarComboExercicios() {
        cmbExercicio.removeAllItems()
        service.obterTreinoAtual()?.exercicios?.forEach {
            cmbExercicio.addItem("${it.nome} [${it.tipo.descricao}]")
        }
    }

    private fun titledBorder(titulo: String) =
        BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color(80, 80, 80)),
            titulo, TitledBorder.LEFT, TitledBorder.TOP,
            Font("SansSerif", Font.BOLD, 12)
        )
}
