package org.example.project.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import org.example.project.data.TreinoRepository
import org.example.project.domain.Exercicio
import org.example.project.domain.Treino
import org.example.project.domain.TreinoId
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

private sealed interface Tela {
    data object Splash : Tela
    data object Login : Tela
    data object Lista : Tela
    data class Detalhe(val id: TreinoId) : Tela
    data object Novo : Tela
}

// Timer State Holder to control the rest countdown globally
private class RestTimerState {
    var totalSeconds by mutableIntStateOf(60)
    var secondsLeft by mutableIntStateOf(0)
    var isRunning by mutableStateOf(false)
    var isVisible by mutableStateOf(false)

    fun start(durationSeconds: Int) {
        totalSeconds = durationSeconds
        secondsLeft = durationSeconds
        isRunning = true
        isVisible = true
    }

    fun pause() {
        isRunning = false
    }

    fun resume() {
        isRunning = true
    }

    fun addTime(seconds: Int) {
        secondsLeft += seconds
        totalSeconds += seconds
    }

    fun reset() {
        secondsLeft = totalSeconds
    }

    fun stop() {
        isRunning = false
        isVisible = false
        secondsLeft = 0
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ControladorTreinoApp(
    context: Context = LocalContext.current,
    repo: TreinoRepository = remember(context) { TreinoRepository(context.applicationContext) }
) {
    var tela by remember { mutableStateOf<Tela>(Tela.Splash) }
    var usuarioLogado by remember { mutableStateOf<String?>(null) }
    val treinos by repo.treinos.collectAsStateWithLifecycle()
    val timerState = remember { RestTimerState() }

    // Coroutine layout timer
    LaunchedEffect(timerState.isRunning, timerState.secondsLeft) {
        if (timerState.isRunning && timerState.secondsLeft > 0) {
            delay(1000L)
            timerState.secondsLeft--
            if (timerState.secondsLeft == 0) {
                timerState.isRunning = false
            }
        }
    }

    ControladorTreinoTheme {
        when (val currentTela = tela) {
            is Tela.Splash -> {
                SplashScreen {
                    tela = Tela.Login
                }
            }
            is Tela.Login -> {
                LoginScreen(repo = repo) { user ->
                    usuarioLogado = user
                    tela = Tela.Lista
                }
            }
            else -> {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = when (val t = currentTela) {
                                        is Tela.Lista -> "MEUS TREINOS"
                                        is Tela.Novo -> "NOVO TREINO"
                                        is Tela.Detalhe -> treinos.firstOrNull { it.id == t.id }?.nome?.uppercase() ?: "TREINO"
                                        else -> "TREINO"
                                    },
                                    fontWeight = FontWeight.Black,
                                    letterSpacing = 1.5.sp,
                                    fontSize = 18.sp,
                                    color = WhiteText
                                )
                            },
                            navigationIcon = {
                                if (currentTela !is Tela.Lista) {
                                    TextButton(
                                        onClick = { tela = Tela.Lista }
                                    ) {
                                        Text(
                                            text = "VOLTAR",
                                            color = CyberCyan,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 1.sp
                                        )
                                    }
                                }
                            },
                            actions = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    modifier = Modifier.padding(end = 8.dp)
                                ) {
                                    usuarioLogado?.let { user ->
                                        Text(
                                            text = user.uppercase(),
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Black,
                                            color = CyberCyan,
                                            letterSpacing = 0.5.sp
                                        )
                                    }
                                    TextButton(
                                        onClick = {
                                            usuarioLogado = null
                                            tela = Tela.Login
                                        },
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text(
                                            text = "SAIR",
                                            color = ErrorColor,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 1.sp
                                        )
                                    }
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = DarkBackground,
                                titleContentColor = WhiteText
                            )
                        )
                    },
                    floatingActionButton = {
                        if (currentTela is Tela.Lista) {
                            ExtendedFloatingActionButton(
                                text = { Text("CRIAR TREINO", fontWeight = FontWeight.Bold, letterSpacing = 1.sp) },
                                icon = { Text("+", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
                                onClick = { tela = Tela.Novo },
                                containerColor = NeonPurple,
                                contentColor = Color.White,
                                shape = RoundedCornerShape(12.dp)
                            )
                        }
                    },
                    bottomBar = {
                        AnimatedVisibility(
                            visible = timerState.isVisible,
                            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
                        ) {
                            RestTimerPill(state = timerState)
                        }
                    },
                    containerColor = DarkBackground
                ) { padding ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .background(DarkBackground)
                    ) {
                        when (currentTela) {
                            is Tela.Lista -> ListaTreinos(
                                treinos = treinos,
                                onAbrir = { tela = Tela.Detalhe(it.id) },
                                onExcluir = { repo.removerTreino(it.id) },
                                onReset = { repo.redefinirTemplates() }
                            )
                            is Tela.Novo -> NovoTreino(onSalvar = { n, d ->
                                repo.adicionarTreino(n.trim(), d.trim())
                                tela = Tela.Lista
                            })
                            is Tela.Detalhe -> {
                                val treino = treinos.firstOrNull { it.id == currentTela.id }
                                if (treino == null) {
                                    tela = Tela.Lista
                                } else {
                                    DetalheTreino(
                                        treino = treino,
                                        timerState = timerState,
                                        onToggle = { repo.alternarConclusao(treino.id, it.id) },
                                        onRemover = { repo.removerExercicio(treino.id, it.id) },
                                        onAdicionar = { n, s, r, c ->
                                            repo.adicionarExercicio(treino.id, n.trim(), s, r, c)
                                        },
                                        onAlterarCarga = { exId, delta ->
                                            repo.alterarCarga(treino.id, exId, delta)
                                        },
                                        onAlterarReps = { exId, delta ->
                                            repo.alterarRepeticoes(treino.id, exId, delta)
                                        },
                                        onAlterarSeries = { exId, delta ->
                                            repo.alterarSeries(treino.id, exId, delta)
                                        }
                                    )
                                }
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ListaTreinos(
    treinos: List<Treino>,
    onAbrir: (Treino) -> Unit,
    onExcluir: (Treino) -> Unit,
    onReset: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Dashboard Card Header (Premium look)
        DashboardPanel(treinos = treinos, onReset = onReset)

        if (treinos.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    "Nenhum treino cadastrado.",
                    color = MutedText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            return
        }

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 80.dp, top = 8.dp)
        ) {
            items(treinos, key = { it.id.value }) { treino ->
                TreinoCard(
                    treino = treino,
                    onClick = { onAbrir(treino) },
                    onExcluir = { onExcluir(treino) }
                )
            }
        }
    }
}

@Composable
private fun DashboardPanel(treinos: List<Treino>, onReset: () -> Unit) {
    val totalExercicios = treinos.sumOf { it.totalExercicios }
    val concluidos = treinos.sumOf { it.concluidos }
    val progressoGeral = if (totalExercicios == 0) 0f else concluidos.toFloat() / totalExercicios
    val volumeTotal = treinos.sumOf { it.volumeConcluido }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFF282834))
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(DarkSurface, DarkBackground)
                    )
                )
                .padding(16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "PAINEL GERAL",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = CyberCyan,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "Resetar",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = NeonPurple,
                        modifier = Modifier.clickable { onReset() }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "${(progressoGeral * 100).toInt()}%",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Black,
                            color = NeonLime
                        )
                        Text(
                            text = "Exercícios concluídos hoje",
                            fontSize = 12.sp,
                            color = MutedText
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "${volumeTotal.toInt()} kg",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Black,
                            color = WhiteText
                        )
                        Text(
                            text = "Volume total levantado",
                            fontSize = 12.sp,
                            color = MutedText
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                val progressoAnimado by animateFloatAsState(
                    targetValue = progressoGeral,
                    animationSpec = tween(600)
                )
                LinearProgressIndicator(
                    progress = { progressoAnimado },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(CircleShape),
                    color = NeonLime,
                    trackColor = Color(0xFF282834)
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$concluidos de $totalExercicios exercícios realizados",
                    fontSize = 11.sp,
                    color = MutedText,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
private fun TreinoCard(
    treino: Treino,
    onClick: () -> Unit,
    onExcluir: () -> Unit
) {
    val progressoAnimado by animateFloatAsState(
        targetValue = treino.progresso,
        animationSpec = tween(500)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        border = BorderStroke(1.dp, Color(0xFF282834))
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = treino.nome.uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp,
                        color = WhiteText
                    )
                    if (treino.descricao.isNotBlank()) {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = treino.descricao,
                            fontSize = 13.sp,
                            color = MutedText
                        )
                    }
                }
                IconButton(
                    onClick = onExcluir,
                    modifier = Modifier.size(24.dp)
                ) {
                    Text("×", color = ErrorColor, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(Modifier.height(16.dp))

            LinearProgressIndicator(
                progress = { progressoAnimado },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = if (treino.progresso == 1f) NeonLime else NeonPurple,
                trackColor = Color(0xFF282834)
            )

            Spacer(Modifier.height(8.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${treino.concluidos}/${treino.totalExercicios} concluídos",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (treino.progresso == 1f) NeonLime else WhiteText
                )

                Text(
                    text = "Vol: ${treino.volumeTotal.toInt()} kg",
                    fontSize = 12.sp,
                    color = CyberCyan,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun NovoTreino(onSalvar: (String, String) -> Unit) {
    var nome by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do Treino (ex: Treino A)") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NeonPurple,
                unfocusedBorderColor = Color(0xFF282834),
                focusedLabelColor = NeonPurple,
                unfocusedLabelColor = MutedText,
                focusedTextColor = WhiteText,
                unfocusedTextColor = WhiteText
            )
        )

        OutlinedTextField(
            value = desc,
            onValueChange = { desc = it },
            label = { Text("Descrição/Foco (ex: Peito e Tríceps)") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NeonPurple,
                unfocusedBorderColor = Color(0xFF282834),
                focusedLabelColor = NeonPurple,
                unfocusedLabelColor = MutedText,
                focusedTextColor = WhiteText,
                unfocusedTextColor = WhiteText
            )
        )

        Button(
            onClick = { if (nome.isNotBlank()) onSalvar(nome, desc) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("SALVAR TREINO", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        }
    }
}

@Composable
private fun DetalheTreino(
    treino: Treino,
    timerState: RestTimerState,
    onToggle: (Exercicio) -> Unit,
    onRemover: (Exercicio) -> Unit,
    onAdicionar: (String, Int, Int, Double) -> Unit,
    onAlterarCarga: (Long, Double) -> Unit,
    onAlterarReps: (Long, Int) -> Unit,
    onAlterarSeries: (Long, Int) -> Unit
) {
    var mostrarFormulario by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Check if entire workout is completed
        val completo = treino.progresso == 1f && treino.totalExercicios > 0
        AnimatedVisibility(
            visible = completo,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            CelebrationCard(volume = treino.volumeTotal)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${treino.concluidos}/${treino.totalExercicios} CONCLUÍDOS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Black,
                color = if (completo) NeonLime else CyberCyan,
                letterSpacing = 1.sp
            )

            Button(
                onClick = { mostrarFormulario = !mostrarFormulario },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (mostrarFormulario) DarkSurfaceVariant else NeonPurple
                ),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = if (mostrarFormulario) "FECHAR" else "+ ADD EXERCÍCIO",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.5.sp
                )
            }
        }

        AnimatedVisibility(
            visible = mostrarFormulario,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFF282834)),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    FormularioExercicio { n, s, r, c ->
                        onAdicionar(n, s, r, c)
                        mostrarFormulario = false
                    }
                }
            }
        }

        HorizontalDivider(color = Color(0xFF282834), thickness = 1.dp)

        if (treino.exercicios.isEmpty()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Nenhum exercício neste treino.",
                    color = MutedText,
                    fontSize = 14.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(top = 12.dp, bottom = 80.dp)
            ) {
                items(treino.exercicios, key = { it.id }) { ex ->
                    CardExercicioItem(
                        ex = ex,
                        onToggle = {
                            onToggle(ex)
                            // Auto suggest/start rest timer on completion of a set/exercise
                            if (!ex.concluido) {
                                timerState.start(60) // Start standard 60s rest
                            }
                        },
                        onRemover = { onRemover(ex) },
                        onAlterarCarga = { delta -> onAlterarCarga(ex.id, delta) },
                        onAlterarReps = { delta -> onAlterarReps(ex.id, delta) },
                        onAlterarSeries = { delta -> onAlterarSeries(ex.id, delta) }
                    )
                }
            }
        }
    }
}

@Composable
private fun CelebrationCard(volume: Double) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E291B)),
        border = BorderStroke(1.dp, NeonLime)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🏆 TREINO CONCLUÍDO! 🏆",
                fontWeight = FontWeight.Black,
                fontSize = 16.sp,
                color = NeonLime,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Bom trabalho! Todos os exercícios concluídos.",
                fontSize = 13.sp,
                color = WhiteText,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Volume total levantado: ${volume.toInt()} kg",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CyberCyan,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun CardExercicioItem(
    ex: Exercicio,
    onToggle: () -> Unit,
    onRemover: () -> Unit,
    onAlterarCarga: (Double) -> Unit,
    onAlterarReps: (Int) -> Unit,
    onAlterarSeries: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (ex.concluido) Color(0xFF16161D) else DarkSurface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (ex.concluido) NeonLime.copy(alpha = 0.3f) else Color(0xFF282834)
        )
    ) {
        Column(Modifier.padding(12.dp)) {
            // Top row with status, title and delete button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = ex.concluido,
                    onCheckedChange = { onToggle() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = NeonLime,
                        checkmarkColor = Color.Black,
                        uncheckedColor = Color(0xFF3E3E50)
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = ex.nome.uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = if (ex.concluido) MutedText else WhiteText,
                        textDecoration = if (ex.concluido) TextDecoration.LineThrough else TextDecoration.None
                    )
                    Text(
                        text = "Vol: ${ex.volume().toInt()} kg",
                        fontSize = 11.sp,
                        color = CyberCyan
                    )
                }

                IconButton(
                    onClick = onRemover,
                    modifier = Modifier.size(28.dp)
                ) {
                    Text("×", color = ErrorColor, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color(0xFF282834).copy(alpha = 0.5f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))

            // Adjusters Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Adjuster 1: Series
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("SÉRIES", fontSize = 9.sp, color = MutedText, fontWeight = FontWeight.Bold)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AdjustButton("-") { onAlterarSeries(-1) }
                        Text(
                            text = "${ex.series}",
                            fontWeight = FontWeight.Black,
                            color = WhiteText,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        AdjustButton("+") { onAlterarSeries(1) }
                    }
                }

                // Adjuster 2: Reps
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("REPETIÇÕES", fontSize = 9.sp, color = MutedText, fontWeight = FontWeight.Bold)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AdjustButton("-") { onAlterarReps(-1) }
                        Text(
                            text = "${ex.repeticoes}",
                            fontWeight = FontWeight.Black,
                            color = WhiteText,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        AdjustButton("+") { onAlterarReps(1) }
                    }
                }

                // Adjuster 3: Carga
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("CARGA (KG)", fontSize = 9.sp, color = MutedText, fontWeight = FontWeight.Bold)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        AdjustButton("-2") { onAlterarCarga(-2.0) }
                        Text(
                            text = "${ex.cargaKg.toInt()}",
                            fontWeight = FontWeight.Black,
                            color = NeonLime,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(horizontal = 6.dp)
                        )
                        AdjustButton("+2") { onAlterarCarga(2.0) }
                    }
                }
            }
        }
    }
}

@Composable
private fun AdjustButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(DarkSurfaceVariant)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = WhiteText,
            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Composable
private fun FormularioExercicio(onAdicionar: (String, Int, Int, Double) -> Unit) {
    var nome by remember { mutableStateOf("") }
    var series by remember { mutableStateOf("3") }
    var reps by remember { mutableStateOf("10") }
    var carga by remember { mutableStateOf("20") }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome do Exercício") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NeonPurple,
                unfocusedBorderColor = Color(0xFF3E3E50),
                focusedLabelColor = NeonPurple,
                unfocusedLabelColor = MutedText,
                focusedTextColor = WhiteText,
                unfocusedTextColor = WhiteText
            )
        )

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = series,
                onValueChange = { series = it.filter(Char::isDigit) },
                label = { Text("Séries") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonPurple,
                    unfocusedBorderColor = Color(0xFF3E3E50),
                    focusedLabelColor = NeonPurple,
                    focusedTextColor = WhiteText,
                    unfocusedTextColor = WhiteText
                )
            )
            OutlinedTextField(
                value = reps,
                onValueChange = { reps = it.filter(Char::isDigit) },
                label = { Text("Reps") },
                modifier = Modifier.weight(1f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonPurple,
                    unfocusedBorderColor = Color(0xFF3E3E50),
                    focusedLabelColor = NeonPurple,
                    focusedTextColor = WhiteText,
                    unfocusedTextColor = WhiteText
                )
            )
            OutlinedTextField(
                value = carga,
                onValueChange = { carga = it.filter { c -> c.isDigit() || c == '.' || c == ',' } },
                label = { Text("Peso (kg)") },
                modifier = Modifier.weight(1.2f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonPurple,
                    unfocusedBorderColor = Color(0xFF3E3E50),
                    focusedLabelColor = NeonPurple,
                    focusedTextColor = WhiteText,
                    unfocusedTextColor = WhiteText
                )
            )
        }

        Button(
            onClick = {
                val s = series.toIntOrNull() ?: 0
                val r = reps.toIntOrNull() ?: 0
                val c = carga.replace(',', '.').toDoubleOrNull() ?: 0.0
                if (nome.isNotBlank() && s > 0 && r > 0) {
                    onAdicionar(nome, s, r, c)
                    nome = ""
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            colors = ButtonDefaults.buttonColors(containerColor = NeonLime, contentColor = Color.Black),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("ADICIONAR AO TREINO", fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp)
        }
    }
}

@Composable
private fun RestTimerPill(state: RestTimerState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(50.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurface),
            border = BorderStroke(1.dp, CyberCyan.copy(alpha = 0.5f)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Circular Timer Indicator
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(36.dp)
                ) {
                    CircularProgressIndicator(
                        progress = {
                            if (state.totalSeconds > 0) {
                                state.secondsLeft.toFloat() / state.totalSeconds
                            } else 0f
                        },
                        color = CyberCyan,
                        strokeWidth = 3.dp,
                        trackColor = Color(0xFF282834),
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = "${state.secondsLeft}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = CyberCyan
                    )
                }

                Column {
                    Text(
                        text = "DESCANSO",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black,
                        color = MutedText,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = String.format("%02d:%02d", state.secondsLeft / 60, state.secondsLeft % 60),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = WhiteText
                    )
                }

                // Control Buttons
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // +15s
                    TextButton(
                        onClick = { state.addTime(15) },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("+15s", color = NeonLime, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    // Play/Pause
                    IconButton(
                        onClick = {
                            if (state.isRunning) state.pause() else state.resume()
                        },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Text(
                            text = if (state.isRunning) "‖" else "▶",
                            color = WhiteText,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Close Timer
                    IconButton(
                        onClick = { state.stop() },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Text("×", color = ErrorColor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Composable
private fun SplashScreen(onTimeout: () -> Unit) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    LaunchedEffect(Unit) {
        delay(2500L) // Wait 2.5 seconds
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Pulsing emblem
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .scale(scale)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(NeonPurple.copy(alpha = 0.6f), Color.Transparent)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "⚡",
                    fontSize = 48.sp,
                    color = NeonLime
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "CONTROLADOR DE TREINO",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = WhiteText,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "SUPERE SEUS LIMITES",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = CyberCyan,
                letterSpacing = 3.sp
            )

            Spacer(modifier = Modifier.height(60.dp))

            CircularProgressIndicator(
                color = NeonLime,
                strokeWidth = 3.dp,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
private fun LoginScreen(
    repo: TreinoRepository,
    onLoginSuccess: (String) -> Unit
) {
    var isLoginTab by remember { mutableStateOf(true) }

    // Input states for Login
    var loginUser by remember { mutableStateOf("") }
    var loginPassword by remember { mutableStateOf("") }

    // Input states for Register
    var regUser by remember { mutableStateOf("") }
    var regPassword by remember { mutableStateOf("") }
    var regConfirmPassword by remember { mutableStateOf("") }

    // Password visibility toggles
    var loginPasswordVisible by remember { mutableStateOf(false) }
    var regPasswordVisible by remember { mutableStateOf(false) }
    var regConfirmPasswordVisible by remember { mutableStateOf(false) }

    // Error and Success messages
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Mini Header
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(DarkSurface),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "⚡",
                    fontSize = 28.sp,
                    color = NeonLime
                )
            }

            Text(
                text = "CONTROLADOR DE TREINO",
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                color = WhiteText,
                letterSpacing = 1.sp
            )
            Text(
                text = if (isLoginTab) "Acesse sua conta para começar" else "Crie sua conta local",
                fontSize = 12.sp,
                color = MutedText,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Card Container
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                border = BorderStroke(1.dp, Color(0xFF282834))
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Tab Headers
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    isLoginTab = true
                                    errorMessage = null
                                    successMessage = null
                                }
                                .padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = "ENTRAR",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isLoginTab) CyberCyan else MutedText
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .height(2.dp)
                                    .fillMaxWidth(0.5f)
                                    .background(if (isLoginTab) CyberCyan else Color.Transparent)
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    isLoginTab = false
                                    errorMessage = null
                                    successMessage = null
                                }
                                .padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = "CADASTRAR",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (!isLoginTab) NeonPurple else MutedText
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .height(2.dp)
                                    .fillMaxWidth(0.5f)
                                    .background(if (!isLoginTab) NeonPurple else Color.Transparent)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Messages
                    errorMessage?.let { msg ->
                        Text(
                            text = msg,
                            color = ErrorColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                    successMessage?.let { msg ->
                        Text(
                            text = msg,
                            color = NeonLime,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }

                    if (isLoginTab) {
                        // Login fields
                        OutlinedTextField(
                            value = loginUser,
                            onValueChange = { loginUser = it },
                            label = { Text("Nome de Usuário") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = CyberCyan,
                                unfocusedBorderColor = Color(0xFF282834),
                                focusedLabelColor = CyberCyan,
                                focusedTextColor = WhiteText,
                                unfocusedTextColor = WhiteText
                            )
                        )

                        OutlinedTextField(
                            value = loginPassword,
                            onValueChange = { loginPassword = it },
                            label = { Text("Senha") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            visualTransformation = if (loginPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                Text(
                                    text = if (loginPasswordVisible) "Ocultar" else "Mostrar",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = CyberCyan,
                                    modifier = Modifier
                                        .clickable { loginPasswordVisible = !loginPasswordVisible }
                                        .padding(8.dp)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = CyberCyan,
                                unfocusedBorderColor = Color(0xFF282834),
                                focusedLabelColor = CyberCyan,
                                focusedTextColor = WhiteText,
                                unfocusedTextColor = WhiteText
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Button(
                            onClick = {
                                val u = loginUser.trim()
                                val p = loginPassword.trim()
                                if (u.isBlank() || p.isBlank()) {
                                    errorMessage = "Preencha todos os campos!"
                                } else {
                                    val logou = repo.validarLogin(u, p)
                                    if (logou) {
                                        onLoginSuccess(u)
                                    } else {
                                        errorMessage = "Usuário ou senha incorretos."
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = CyberCyan, contentColor = Color.Black),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("ENTRAR", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                        }
                    } else {
                        // Register fields
                        OutlinedTextField(
                            value = regUser,
                            onValueChange = { regUser = it },
                            label = { Text("Nome de Usuário") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonPurple,
                                unfocusedBorderColor = Color(0xFF282834),
                                focusedLabelColor = NeonPurple,
                                focusedTextColor = WhiteText,
                                unfocusedTextColor = WhiteText
                            )
                        )

                        OutlinedTextField(
                            value = regPassword,
                            onValueChange = { regPassword = it },
                            label = { Text("Nova Senha") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            visualTransformation = if (regPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                Text(
                                    text = if (regPasswordVisible) "Ocultar" else "Mostrar",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = NeonPurple,
                                    modifier = Modifier
                                        .clickable { regPasswordVisible = !regPasswordVisible }
                                        .padding(8.dp)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonPurple,
                                unfocusedBorderColor = Color(0xFF282834),
                                focusedLabelColor = NeonPurple,
                                focusedTextColor = WhiteText,
                                unfocusedTextColor = WhiteText
                            )
                        )

                        OutlinedTextField(
                            value = regConfirmPassword,
                            onValueChange = { regConfirmPassword = it },
                            label = { Text("Confirmar Senha") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            visualTransformation = if (regConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                Text(
                                    text = if (regConfirmPasswordVisible) "Ocultar" else "Mostrar",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = NeonPurple,
                                    modifier = Modifier
                                        .clickable { regConfirmPasswordVisible = !regConfirmPasswordVisible }
                                        .padding(8.dp)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = NeonPurple,
                                unfocusedBorderColor = Color(0xFF282834),
                                focusedLabelColor = NeonPurple,
                                focusedTextColor = WhiteText,
                                unfocusedTextColor = WhiteText
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Button(
                            onClick = {
                                val u = regUser.trim()
                                val p = regPassword.trim()
                                val cp = regConfirmPassword.trim()

                                when {
                                    u.isBlank() || p.isBlank() || cp.isBlank() -> {
                                        errorMessage = "Preencha todos os campos!"
                                    }
                                    p != cp -> {
                                        errorMessage = "As senhas não coincidem!"
                                    }
                                    p.length < 4 -> {
                                        errorMessage = "A senha deve ter pelo menos 4 caracteres!"
                                    }
                                    else -> {
                                        val cadastrou = repo.cadastrarUsuario(u, p)
                                        if (cadastrou) {
                                            successMessage = "Conta cadastrada com sucesso!"
                                            errorMessage = null
                                            // Reset inputs
                                            regUser = ""
                                            regPassword = ""
                                            regConfirmPassword = ""
                                            // Auto switch to login tab
                                            isLoginTab = true
                                        } else {
                                            errorMessage = "Usuário já cadastrado!"
                                        }
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = NeonPurple, contentColor = Color.White),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("CADASTRAR", fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                        }
                    }
                }
            }
        }
    }
}


