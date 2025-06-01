package com.example.k

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.k.ui.theme.KTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KTheme {
                App1404()
            }
        }
    }
}

@Composable
fun App1404() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            // Título
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(36.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    "App Exemplo diferente modificação do dia 07/04",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            // Estados dos campos
            val nome = remember { mutableStateOf("") }
            val Senha = remember { mutableStateOf("") }

            // Campos de entrada
            InputField("Nome:", nome)
            InputField("Senha:", Senha)


            Spacer(modifier = Modifier.height(20.dp))

            // Botões
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        // Ação de cadastro
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text("Cadastrar", color = Color.Black)
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        // Ação de cancelamento
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text("Cancelar", color = Color.Black)
                }
            }
        }
    }
}

@Composable
fun InputField(label: String, state: MutableState<String>, imeAction: ImeAction = ImeAction.Next) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        TextField(
            value = state.value,
            onValueChange = { state.value = it },
            label = { Text(label) },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = imeAction),
            colors = TextFieldDefaults.colors()
        )
    }
}
