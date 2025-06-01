package com.example.appaula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.appaula.ui.theme.AppAulaTheme
import org.w3c.dom.Text
import androidx.compose.ui.text.TextStyle


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppAulaTheme {
                AppAula()
            }
        }
    }
}

@Composable
fun AppAula() {
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
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    "App Login",
                    color = Color.White,
                    style = TextStyle(fontWeight = FontWeight(500)) // Aplica o peso da fonte
                )
            }
            Spacer(modifier = Modifier.height(36.dp))
            // Campo de Usuario
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                TextField(
                    value = TextFieldValue(""),
                    onValueChange = {},
                    label = { Text("Usuario:") },

                )
            }
            Spacer(modifier = Modifier.height(36.dp))
            // Campo de Senha
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {

                TextField(
                    value = TextFieldValue(""),
                    onValueChange = {},
                    label = { Text("Senha:")  },


                    )

            }

            // Botão Cadastrar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray // Cor do fundo do botão
                    )
                ) {
                    Text("Cadastrar", color = Color.Black) // Texto do botão em preto
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppAulaPreview() {
    AppAulaTheme {
        AppAula()
    }
}

