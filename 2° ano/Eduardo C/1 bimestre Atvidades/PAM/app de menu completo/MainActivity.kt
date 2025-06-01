package com.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    // Usando Box com fundo preto
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black) // Define o fundo preto
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center) // Alinha o conteúdo no centro
        ) {
            // Título do formulário
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    "App Exemplo",
                    color = Color.White,
                    style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight(1000)) // Aplica o peso da fonte
                )
            }
            Spacer(modifier = Modifier.height(100.dp))

            // Campo de Nome
            val nome = remember { androidx.compose.runtime.mutableStateOf("") }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = nome.value,
                    onValueChange = { nome.value = it },
                    label = { Text("Nome:") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Campo de Endereço
            val endereco = remember { androidx.compose.runtime.mutableStateOf("") }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = endereco.value,
                    onValueChange = { endereco.value = it },
                    label = { Text("Endereço:") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Campo de Bairro
            val bairro = remember { androidx.compose.runtime.mutableStateOf("") }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = bairro.value,
                    onValueChange = { bairro.value = it },
                    label = { Text("Bairro:") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Campo de CEP
            val cep = remember { androidx.compose.runtime.mutableStateOf("") }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = cep.value,
                    onValueChange = { cep.value = it },
                    label = { Text("CEP:") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Campo de Cidade
            val cidade = remember { androidx.compose.runtime.mutableStateOf("") }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = cidade.value,
                    onValueChange = { cidade.value = it },
                    label = { Text("Cidade:") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Campo de Estado
            val estado = remember { androidx.compose.runtime.mutableStateOf("") }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = estado.value,
                    onValueChange = { estado.value = it },
                    label = { Text("Estado:") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                )
            }

            // Linha com os dois botões alinhados horizontalmente
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center // Alinha os botões no centro, sem muito espaçamento
            ) {
                Button(
                    onClick = {
                        // Lógica para cadastrar
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray // Cor do fundo do botão
                    ),
                ) {
                    Text("Cadastrar", color = Color.Black) // Texto do botão em preto
                }

                Spacer(modifier = Modifier.width(8.dp)) // Espaço pequeno entre os botões

                Button(
                    onClick = {
                        // Lógica para cancelar
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray // Cor do fundo do botão
                    ),
                ) {
                    Text("Cancelar", color = Color.Black) // Texto do botão em preto
                }
            }
        }
    }
}

