package org.example.project.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.data.TreinoRepository
import org.example.project.domain.Exercicio
import org.example.project.domain.Treino
import org.example.project.domain.TreinoId

private sealed interface Tela {
    data object Lista : Tela
    data class Detalhe(val id: TreinoId) : Tela
    data object Novo : Tela
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ControladorTreinoApp(repo: TreinoRepository = remember { TreinoRepository() }) {
    var tela by remember { mutableStateOf<Tela>(Tela.Lista) }
    val treinos by repo.treinos.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        when (val t = tela) {
                            is Tela.Lista -> "Meus Treinos"
                            is Tela.Novo -> "Novo Treino"
                            is Tela.Detalhe -> treinos.firstOrNull { it.id == t.id }?.nome ?: "Treino"
                        }
                    )
                },
                navigationIcon = {
                    if (tela !is Tela.Lista) {
                        TextButton(onClick = { tela = Tela.Lista }) { Text("Voltar") }
                    }
                }
            )
        },
        floatingActionButton = {
            if (tela is Tela.Lista) {
                ExtendedFloatingActionButton(
                    text = { Text("Novo") }, icon = {},
                    onClick = { tela = Tela.Novo }
                )
            }
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            when (val t = tela) {
                is Tela.Lista -> ListaTreinos(
                    treinos = treinos,
                    onAbrir = { tela = Tela.Detalhe(it.id) },
                    onExcluir = { repo.removerTreino(it.id) }
                )
                is Tela.Novo -> NovoTreino(onSalvar = { n, d ->
                    repo.adicionarTreino(n.trim(), d.trim()); tela = Tela.Lista
                })
                is Tela.Detalhe -> {
                    val treino = treinos.firstOrNull { it.id == t.id }
                    if (treino == null) tela = Tela.Lista
                    else DetalheTreino(
                        treino = treino,
                        onToggle = { repo.alternarConclusao(treino.id, it.id) },
                        onRemover = { repo.removerExercicio(treino.id, it.id) },
                        onAdicionar = { n, s, r, c ->
                            repo.adicionarExercicio(treino.id, n.trim(), s, r, c)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ListaTreinos(treinos: List<Treino>, onAbrir: (Treino) -> Unit, onExcluir: (Treino) -> Unit) {
    if (treinos.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Nenhum treino cadastrado.")
        }
        return
    }
    LazyColumn(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(treinos, key = { it.id.value }) { treino ->
            ElevatedCard(onClick = { onAbrir(treino) }) {
                Column(Modifier.padding(16.dp)) {
                    Text(treino.nome, style = MaterialTheme.typography.titleMedium)
                    if (treino.descricao.isNotBlank()) {
                        Text(treino.descricao, style = MaterialTheme.typography.bodySmall)
                    }
                    Spacer(Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { treino.progresso },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(4.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("${treino.concluidos}/${treino.totalExercicios} concluídos")
                        TextButton(onClick = { onExcluir(treino) }) { Text("Excluir") }
                    }
                }
            }
        }
    }
}

@Composable
private fun NovoTreino(onSalvar: (String, String) -> Unit) {
    var nome by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(nome, { nome = it }, label = { Text("Nome") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(desc, { desc = it }, label = { Text("Descrição") }, modifier = Modifier.fillMaxWidth())
        Button(
            onClick = { if (nome.isNotBlank()) onSalvar(nome, desc) },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Salvar") }
    }
}

@Composable
private fun DetalheTreino(
    treino: Treino,
    onToggle: (Exercicio) -> Unit,
    onRemover: (Exercicio) -> Unit,
    onAdicionar: (String, Int, Int, Double) -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        FormularioExercicio(onAdicionar)
        Spacer(Modifier.height(16.dp))
        HorizontalDivider()
        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(treino.exercicios, key = { it.id }) { ex ->
                ListItem(
                    headlineContent = { Text(ex.nome) },
                    supportingContent = {
                        Text("${ex.series}x${ex.repeticoes} • ${ex.cargaKg} kg • vol ${ex.volume()}")
                    },
                    leadingContent = {
                        Checkbox(checked = ex.concluido, onCheckedChange = { onToggle(ex) })
                    },
                    trailingContent = { TextButton(onClick = { onRemover(ex) }) { Text("Remover") } }
                )
            }
        }
    }
}

@Composable
private fun FormularioExercicio(onAdicionar: (String, Int, Int, Double) -> Unit) {
    var nome by remember { mutableStateOf("") }
    var series by remember { mutableStateOf("3") }
    var reps by remember { mutableStateOf("10") }
    var carga by remember { mutableStateOf("20") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(nome, { nome = it }, label = { Text("Exercício") }, modifier = Modifier.fillMaxWidth())
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                series, { series = it.filter(Char::isDigit) },
                label = { Text("Séries") }, modifier = Modifier.weight(1f), singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                reps, { reps = it.filter(Char::isDigit) },
                label = { Text("Reps") }, modifier = Modifier.weight(1f), singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                carga, { carga = it.filter { c -> c.isDigit() || c == '.' || c == ',' } },
                label = { Text("Carga") }, modifier = Modifier.weight(1f), singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
        }
        Button(
            onClick = {
                val s = series.toIntOrNull() ?: 0
                val r = reps.toIntOrNull() ?: 0
                val c = carga.replace(',', '.').toDoubleOrNull() ?: 0.0
                if (nome.isNotBlank() && s > 0 && r > 0) {
                    onAdicionar(nome, s, r, c); nome = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Adicionar exercício") }
    }
}
